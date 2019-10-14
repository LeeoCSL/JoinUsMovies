package com.lr.welcomejoinus.Activity.MovieDetails;

import android.content.Context;
import com.lr.welcomejoinus.Models.MovieDetailed;
import retrofit2.Response;

public interface MVPMovieDetails {

    interface View{
        void movieDetailsOk(Response<MovieDetailed> response);
        void movieDetailsError(Throwable t);
        void movieDetailsFullOk(Response<MovieDetailed> response);
        void movieDetailsFullError(Throwable t);
    }

    interface Presenter{
        void getMovieDetails(Context ctx, String apiKey, String title);
        void getMovieDetailsOk(Response<MovieDetailed> response);
        void getMovieDetailsError(Throwable t);
        void getMovieDetailsFull(Context ctx, String apiKey, String title);
        void getMovieDetailsFullOk(Response<MovieDetailed> response);
        void getMovieDetailsFullError(Throwable t);
        void setView(MVPMovieDetails.View view);
    }
}
