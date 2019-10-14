package com.lr.welcomejoinus.Activity.Search;

import android.content.Context;
import com.lr.welcomejoinus.Models.Search;
import retrofit2.Response;

public interface MVPSearch {

    interface View{
        void callPrevPageOk(Response<Search> response);
        void callPrevPageError(Throwable t);
        void callProxPageOk(Response<Search> response);
        void callProxPageError(Throwable t);
    }

    interface Presenter{
        void callPrevPage(Context ctx, String apiKey, String title, int page);
        void callPrevPageOk(Response<Search> response);
        void callPrevPageError(Throwable t);
        void callProxPage(Context ctx, String apiKey, String title, int page);
        void callProxPageOK(Response<Search> response);
        void callProxPageError(Throwable t);
        void setView(MVPSearch.View view);
    }
}
