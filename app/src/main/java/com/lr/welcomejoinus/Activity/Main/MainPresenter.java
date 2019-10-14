package com.lr.welcomejoinus.Activity.Main;

import android.content.Context;
import com.lr.welcomejoinus.Models.Search;
import retrofit2.Response;

public class MainPresenter implements MVPMain.Presenter {

    MVPMain.View view;
    MainModel model;

    public MainPresenter(){
        model = new MainModel(this);
    }

    public void getMovies(Context ctx, String apiKey, String searchTitulo){
        model.getMovies(ctx, apiKey, searchTitulo);
    }

    @Override
    public void getMoviesOK(Response<Search> response) {
        view.getMoviesCallOk(response);
    }

    @Override
    public void getMoviesError(Throwable t) {
    view.getMoviesCallError(t);
    }

    public void setView(MVPMain.View view) {
        this.view = view;
    }
}
