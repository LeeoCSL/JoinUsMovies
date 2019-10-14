package com.lr.welcomejoinus.Activity.Search;

import android.content.Context;
import com.lr.welcomejoinus.Models.Search;
import retrofit2.Response;

public class SearchPresenter implements MVPSearch.Presenter {

    MVPSearch.View view;
    SearchModel model;

    public SearchPresenter(){
        model = new SearchModel(this);
    }

    @Override
    public void callPrevPage(Context ctx, String apiKey, String title, int page) {
        model.callPrevPage(ctx, apiKey, title, page);
    }

    @Override
    public void callPrevPageOk(Response<Search> response) {
        view.callPrevPageOk(response);
    }

    @Override
    public void callPrevPageError(Throwable t) {
        view.callPrevPageError(t);
    }


    @Override
    public void callProxPage(Context ctx, String apiKey, String title, int page) {
        model.callProxPage(ctx, apiKey, title, page);
    }

    @Override
    public void callProxPageOK(Response<Search> response) {
        view.callProxPageOk(response);
    }

    @Override
    public void callProxPageError(Throwable t) {
        view.callProxPageError(t);
    }

    @Override
    public void setView(MVPSearch.View view) {
        this.view = view;
    }
}
