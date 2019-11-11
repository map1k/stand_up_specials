package com.netflex.stand_up.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Special implements Comparable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name, author, language;
    int year;
    Ratings [] score;
    double imdbRating;
    double imdbVotes;
    double ultimateScore;

    public Special(String name, String author, int year, String language) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.language = language;
    }

    public String getRatings(){
        String ret = "";
        for (Ratings ratings : score) {
            ret += "\t";
            ret += ratings.toString() + "\n";
        }
        return ret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Ratings [] getScore() {
        return score;
    }

    public void setScore(Ratings [] score) {
        this.score = score;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public double getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(double imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setUltimateScore(double ultimateScore) {
        this.ultimateScore = ultimateScore;
    }

    public Double getUltimateScore(){
        double v = getImdbRating() + getImdbVotes() / 5000;
        setUltimateScore(v);
        return v;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Special{").append("\n");
        sb.append("\t").append("name='").append(name).append('\'').append("\n");
        sb.append("\t").append("language='").append(language).append('\'').append("\n");
        sb.append("\t").append("year=").append(year).append("\n");
        sb.append(score == null ? "null" : getRatings());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        return this.getUltimateScore().compareTo(((Special) o).getUltimateScore());
    }
}

