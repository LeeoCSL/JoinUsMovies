package com.lr.welcomejoinus.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.lr.welcomejoinus.Activity.Main.MainActivity;
import com.lr.welcomejoinus.Application.CustomApplication;
import com.lr.welcomejoinus.R;
import com.lr.welcomejoinus.RealmFiles.RealmModels.MovieRealm;
import com.lr.welcomejoinus.RealmFiles.RealmModels.MoviesListRealm;
import io.realm.Realm;

import java.util.ArrayList;
import java.util.List;


public class SplashScreen extends AppCompatActivity {

//    RealmConfiguration realmConfig = new RealmConfiguration.Builder(SplashScreen.this).build();
//    Realm realm = Realm.getInstance(realmConfig);

    Realm realm = Realm.getDefaultInstance();

    private ProgressBar mProgress;

    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        loadUI();
        getDataFromRealm();

    }

    private void loadUI() {

        mProgress = findViewById(R.id.mProgress);

    }

    private void getDataFromRealm() {
        List<MovieRealm> moviesRealm = new ArrayList<>();
        MoviesListRealm moviesListRealm = new MoviesListRealm();

        moviesRealm = realm.where(MovieRealm.class).findAll();

        for(int i = 0; i < moviesRealm.size(); i++){
            moviesListRealm.addMovie(moviesRealm.get(i));
        }
        CustomApplication.moviesListRealm = moviesListRealm;
//        Toast.makeText(this, CustomApplication.moviesListRealm.getMoviesList().get(0).getTitle(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "aqui", Toast.LENGTH_SHORT).show();


        changeScreen();
    }

    private void changeScreen() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus++;
                    android.os.SystemClock.sleep(50);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();                    }
                });
            }
        }).start();
    }


}
