package com.lr.welcomejoinus.RealmFiles.RealmModels;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MoviesListRealm extends RealmObject {

    RealmList<MovieRealm> moviesList = new RealmList<>();

    public MoviesListRealm() {
    }

    public MoviesListRealm(RealmList<MovieRealm> moviesList) {
        this.moviesList = moviesList;
    }

    public RealmList<MovieRealm> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(RealmList<MovieRealm> moviesList) {
        this.moviesList = moviesList;
    }

    public void addMovie(MovieRealm movieRealm){
        moviesList.add(movieRealm);
    }
}
