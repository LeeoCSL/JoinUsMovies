package com.lr.welcomejoinus.Activity.Search;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.lr.welcomejoinus.Application.CustomApplication;
import com.lr.welcomejoinus.Models.Search;
import com.lr.welcomejoinus.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchModel {

    MVPSearch.Presenter presenter;

    public SearchModel(MVPSearch.Presenter presenter){
        this.presenter = presenter;
    }

    public void callPrevPage(Context ctx, String apiKey, String title, int page){
        Call<Search> call = new RetrofitInit(ctx).getMoviesService().getMoviesPage(apiKey, title.trim(), "movie", page);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                presenter.callPrevPageOk(response);
            }


            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                presenter.callPrevPageError(t);

            }
        });
    }

    public void callProxPage(Context ctx, String apiKey, String title, int page){
        Call<Search> call = new RetrofitInit(ctx).getMoviesService().getMoviesPage(apiKey, title.trim(), "movie", page);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                presenter.callProxPageOK(response);
            }


            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                presenter.callProxPageError(t);
            }
        });
    }

}
