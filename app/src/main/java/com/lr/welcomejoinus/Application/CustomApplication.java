package com.lr.welcomejoinus.Application;


import android.app.Application;
import android.content.Context;
import com.lr.welcomejoinus.Models.Movie;
import com.lr.welcomejoinus.Models.MovieDetailed;
import com.lr.welcomejoinus.RealmFiles.RealmModels.MoviesListRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import java.util.ArrayList;
import java.util.List;



public class CustomApplication extends Application {

    public static List<Movie> movies = new ArrayList<>();
    public static List<MovieDetailed> favMovies = new ArrayList<>();
    public static String search;
    public static MoviesListRealm moviesListRealm = new MoviesListRealm();

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
//        Realm.deleteRealm(Realm.getDefaultConfiguration());
//        RealmConfiguration config = new RealmConfiguration
//                .Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    public static void setMovies(List<Movie> movies){
        CustomApplication.movies = movies;
    }

    public static List<Movie> getMovies(){
        return movies;
    }

    public static List<MovieDetailed> getFavMovies() {
        return favMovies;
    }

    public static void setFavMovies(List<MovieDetailed> favMovies) {
        CustomApplication.favMovies = favMovies;
    }

    public static void addFavMovie(MovieDetailed movie){
        CustomApplication.favMovies.add(movie);
    }

    public static void removeFavMovie(MovieDetailed movie){
        CustomApplication.favMovies.remove(movie);
    }

    public static String getSearch() {
        return search;
    }

    public static void setSearchTerm(String search) {
        CustomApplication.search = search;
    }

    public static MoviesListRealm getMoviesListRealm() {
        return moviesListRealm;
    }

    public static void setMoviesListRealm(MoviesListRealm moviesListRealm) {
        CustomApplication.moviesListRealm = moviesListRealm;
    }
}