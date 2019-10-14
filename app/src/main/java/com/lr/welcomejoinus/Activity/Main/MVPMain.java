package com.lr.welcomejoinus.Activity.Main;

import android.content.Context;
import com.lr.welcomejoinus.Models.Search;
import retrofit2.Response;

public interface MVPMain {

    interface View{
        void getMoviesCallOk(Response<Search> response);
        void getMoviesCallError(Throwable t);
    }

    interface Presenter{
        void getMovies(Context ctx, String apiKey, String searchTitulo);
        void getMoviesOK(Response<Search> response);
        void getMoviesError(Throwable t);
        void setView(MVPMain.View view);
    }

}
