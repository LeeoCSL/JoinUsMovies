package com.lr.welcomejoinus.Activity.MovieDetails;

import android.content.Context;
import android.util.Log;
import com.lr.welcomejoinus.Models.MovieDetailed;
import com.lr.welcomejoinus.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsModel {

    MVPMovieDetails.Presenter presenter;

    public MovieDetailsModel(MVPMovieDetails.Presenter presenter){
        this.presenter = presenter;
    }


    public void getMovieDetails(Context ctx, String apiKey, String title){
        Call<MovieDetailed> call = new RetrofitInit(ctx).getMoviesService().getMovieDetail(apiKey, title);
        call.enqueue(new Callback<MovieDetailed>() {
            @Override
            public void onResponse(Call<MovieDetailed> call, Response<MovieDetailed> response) {
                presenter.getMovieDetailsOk(response);

            }


            @Override
            public void onFailure(Call<MovieDetailed> call, Throwable t) {
                presenter.getMovieDetailsError(t);

            }
        });
    }

    public void getMovieDetailsFull(Context ctx, String apiKey, String title){
        Call<MovieDetailed> call = new RetrofitInit(ctx).getMoviesService().getMovieDetailFullPlot(apiKey, title, "full");
        call.enqueue(new Callback<MovieDetailed>() {
            @Override
            public void onResponse(Call<MovieDetailed> call, Response<MovieDetailed> response) {
                presenter.getMovieDetailsFullOk(response);
            }


            @Override
            public void onFailure(Call<MovieDetailed> call, Throwable t) {
                presenter.getMovieDetailsFullError(t);

            }
        });
    }
}
