package com.lr.welcomejoinus.RealmFiles.RealmModels;

import io.realm.RealmObject;

public class MovieRealm extends RealmObject {

    String imdbId;
    String title;
    String poster;
    String year;
    String director;
    String genre;
    String imdbRate;
    String plot;
    boolean fav;

    public MovieRealm() {
    }

    public MovieRealm(String imdbId, String title, String poster, String year, String director, String genre, String imdbRate, String plot, boolean fav) {
        this.imdbId = imdbId;
        this.title = title;
        this.poster = poster;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.imdbRate = imdbRate;
        this.plot = plot;
        this.fav = fav;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImdbRate() {
        return imdbRate;
    }

    public void setImdbRate(String imdbRate) {
        this.imdbRate = imdbRate;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
