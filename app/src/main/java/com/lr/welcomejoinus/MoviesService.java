package com.lr.welcomejoinus;


import com.lr.welcomejoinus.Models.MovieDetailed;
import com.lr.welcomejoinus.Models.Search;
import retrofit2.Call;
import retrofit2.http.*;

public interface MoviesService {
    @GET("/")
    Call<Search> getMovies(@Query ("apikey") String apikey, @Query ("s") String search, @Query ("type") String type);

    @GET("/")
    Call<Search> getMoviesPage(@Query ("apikey") String apikey, @Query ("s") String search, @Query ("type") String type, @Query("page") int page);

    @GET("/")
    Call<MovieDetailed> getMovieDetail(@Query ("apikey") String apikey, @Query ("t") String search);
    @GET("/")
    Call<MovieDetailed> getMovieDetailFullPlot(@Query ("apikey") String apikey, @Query ("t") String search, @Query("plot") String plot);


}
