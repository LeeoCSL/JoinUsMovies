package com.lr.welcomejoinus.Activity.Main;

import android.content.Context;
import com.lr.welcomejoinus.Models.Search;
import com.lr.welcomejoinus.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel {

    MVPMain.Presenter presenter;

    public MainModel(MVPMain.Presenter presenter){
        this.presenter = presenter;
    }

    public void getMovies(Context ctx, String apiKey, String searchTitulo){
        Call<Search> call = new RetrofitInit(ctx).getMoviesService().getMovies(apiKey, searchTitulo, "movie");
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                presenter.getMoviesOK(response);
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                presenter.getMoviesError(t);

            }
        });
    }
}
