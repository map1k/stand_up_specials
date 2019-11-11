package com.netflex.stand_up.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class SpecialImdbInfo {

    @JsonProperty("Title") public String title;

    @JsonProperty("Year") public String year;

    @JsonProperty("Rated") public String rated;

    @JsonProperty("Released") public String released;

    @JsonProperty("Runtime") public String runtime;

    @JsonProperty("Genre") public String genre;

    @JsonProperty("Director") public String director;

    @JsonProperty("Writer") public String writer;

    @JsonProperty("Actors") public String actors;

    @JsonProperty("Plot") public String plot;

    @JsonProperty("Language") public String language;

    @JsonProperty("Country") public String country;

    @JsonProperty("Awards") public String awards;

    @JsonProperty("Poster") public String poster;

    @JsonProperty("Ratings") public Ratings [] ratings;

    @JsonProperty("Metascore") public String metascore;

    @JsonProperty("imdbRating") public String imdbRating;

    @JsonProperty("imdbVotes") public String imdbVotes;

    @JsonProperty("imdbID") public String imdbId;

    @JsonProperty("Type") public String type;

    @JsonProperty("DVD") public String dvd;

    @JsonProperty("BoxOffice") public String box;

    @JsonProperty("Production") public String production;

    @JsonProperty("Website") public String website;

    @JsonProperty("Response") public String response;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SpecialImdbInfo{");
        sb.append("title='").append(title).append('\'');
        sb.append(", year='").append(year).append('\'');
        sb.append(", rated='").append(rated).append('\'');
        sb.append(", released='").append(released).append('\'');
        sb.append(", runtime='").append(runtime).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", director='").append(director).append('\'');
        sb.append(", writer='").append(writer).append('\'');
        sb.append(", actors='").append(actors).append('\'');
        sb.append(", plot='").append(plot).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", awards='").append(awards).append('\'');
        sb.append(", poster='").append(poster).append('\'');
        sb.append(", ratings=").append(ratings == null ? "null" : Arrays.asList(ratings).toString());
        sb.append(", metascore='").append(metascore).append('\'');
        sb.append(", imdbRating='").append(imdbRating).append('\'');
        sb.append(", imdbVotes='").append(imdbVotes).append('\'');
        sb.append(", imdbId='").append(imdbId).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", dvd='").append(dvd).append('\'');
        sb.append(", box='").append(box).append('\'');
        sb.append(", production='").append(production).append('\'');
        sb.append(", website='").append(website).append('\'');
        sb.append(", response='").append(response).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

