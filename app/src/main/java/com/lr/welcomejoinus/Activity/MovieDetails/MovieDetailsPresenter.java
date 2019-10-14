package com.lr.welcomejoinus.Activity.MovieDetails;

import android.content.Context;
import com.lr.welcomejoinus.Models.MovieDetailed;
import retrofit2.Response;

public class MovieDetailsPresenter implements MVPMovieDetails.Presenter{

    MVPMovieDetails.View view;
    MovieDetailsModel model;

    public MovieDetailsPresenter(){
        model = new MovieDetailsModel(this);
    }

    @Override
    public void getMovieDetails(Context ctx, String apiKey, String title) {
        model.getMovieDetails(ctx, apiKey, title);
    }

    @Override
    public void getMovieDetailsOk(Response<MovieDetailed> response) {
        view.movieDetailsOk(response);
    }

    @Override
    public void getMovieDetailsError(Throwable t) {
        view.movieDetailsError(t);
    }

    @Override
    public void getMovieDetailsFull(Context ctx, String apiKey, String title) {
        model.getMovieDetailsFull(ctx, apiKey, title);
    }

    @Override
    public void getMovieDetailsFullOk(Response<MovieDetailed> response) {
        view.movieDetailsFullOk(response);
    }

    @Override
    public void getMovieDetailsFullError(Throwable t) {
        view.movieDetailsFullError(t);
    }

    @Override
    public void setView(MVPMovieDetails.View view) {
this.view = view;
    }
}
