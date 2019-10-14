package com.lr.welcomejoinus;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;

import com.lr.welcomejoinus.Application.CustomApplication;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInit {

    private Retrofit retrofit;
    //    final String BASE_URL = "http://www.omdbapi.com/?apikey=63537a4&";
    final String BASE_URL = "http://www.omdbapi.com/";

    public RetrofitInit(Context activity) {

        final CustomApplication customApplication = (CustomApplication) activity.getApplicationContext();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                String stringurl = original.url().toString();
                stringurl = stringurl.replace("%26", "&");
                Request request = requestBuilder
                        .url(stringurl)
                        .build();
                return chain.proceed(request);
            }
        });

        client.addInterceptor(interceptor);

        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();
    }


    public MoviesService getMoviesService() {
        return retrofit.create(MoviesService.class);
    }
}