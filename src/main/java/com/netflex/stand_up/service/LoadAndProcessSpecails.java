package com.netflex.stand_up.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflex.stand_up.model.Special;
import com.netflex.stand_up.model.SpecialImdbInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class LoadAndProcessSpecails {

    public List<Special> getProcessedSpecials() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_original_stand-up_comedy_specials_distributed_by_Netflix").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("Loading " + doc.title());
        List<Special> list = getSpesial(doc);
        List<Special> listImdb = new ArrayList<>();

        String apiKey = "&apikey=ee2ac704";
        String urlStart = "http://www.omdbapi.com/?t=";
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        for (Special special : list) {
            String r = urlStart +
                    special.getName().replace(" ", "+")
                            .replace(":", "%3A") +
                    "&y=" + special.getYear() +
                    apiKey;


            try {
                URL url = new URL(r);
                SpecialImdbInfo s = mapper.readValue(url, SpecialImdbInfo.class);


                if(s.title == null || s.imdbRating.equals("N/A")) continue;

                special.setScore(s.ratings);
                special.setImdbRating(Double.parseDouble(s.imdbRating));
                special.setImdbVotes(Double.parseDouble(s.imdbVotes.replace(",", "")));
                listImdb.add(special);
            } catch (FileNotFoundException e) {} catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(listImdb);

        return listImdb;
    }

    private static List<Special> getSpesial(Document doc) {

        List<Special> list = new ArrayList<>();

        for (Element table : doc.select("table")) {

            for (Element row : table.select("tr")) {

                Elements tds = row.select("td");
                if(tds.isEmpty()) continue;
                if(tds.size() != 4) continue;
                String author;
                String text = tds.get(0).text();
                if(text.contains("["))
                    text = text.substring(0, text.indexOf("["));
                if(text.contains(":"))
                    author = text.substring(0, text.indexOf(": "));
                else
                {
                    String[] arr = text.split("\\s+");
                    author = arr[0] + " " + arr[1];
                }
                String year = tds.get(1).text().substring(tds.get(1).text().indexOf(", ") + 2);
                if(year.contains("["))
                    year = year.substring(0, year.indexOf("["));
                if(!year.chars().allMatch( Character::isDigit )) continue;
                Special nevv = new Special(
                        text,
                        author,
                        Integer.valueOf(year),
                        tds.get(3).text()
                );
                list.add(nevv);
            }
        }
        return list;

    }
}
