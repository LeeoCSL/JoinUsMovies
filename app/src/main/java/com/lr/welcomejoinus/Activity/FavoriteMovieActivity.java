package com.lr.welcomejoinus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.lr.welcomejoinus.Activity.Main.MainActivity;
import com.lr.welcomejoinus.Activity.MovieDetails.MovieDetails;
import com.lr.welcomejoinus.Activity.Search.SearchResult;
import com.lr.welcomejoinus.Application.CustomApplication;
import com.lr.welcomejoinus.Models.Movie;
import com.lr.welcomejoinus.Models.MovieDetailed;
import com.lr.welcomejoinus.R;
import com.lr.welcomejoinus.RealmFiles.RealmModels.MovieRealm;
import com.lr.welcomejoinus.RealmFiles.RealmModels.MoviesListRealm;
import com.squareup.picasso.Picasso;
import io.realm.Realm;
import retrofit2.Response;

import java.util.List;

public class FavoriteMovieActivity extends AppCompatActivity {



    MoviesListRealm moviesListRealm = CustomApplication.getMoviesListRealm();
    MovieRealm movieRealm;
    int position;
    TextView tvTitle;
    TextView tvYear;
    TextView tvGenre;
    TextView tvDirector;
    TextView tvRate;
    TextView tvPlot;
    ImageView imgPoster;
    String apikey = "63537a4&";

    ImageView imgFav;
    boolean fav = false;

    //    RealmConfiguration realmConfig = new RealmConfiguration
//            .Builder(this).build();
//    Realm realm = Realm.getInstance(realmConfig);
    Realm realm = Realm.getDefaultInstance();
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return super.onSupportNavigateUp();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        if (intent.hasExtra("position")) {
            position = intent.getExtras().getInt("position");
        }


        loadUI();
        movieRealm = moviesListRealm.getMoviesList().get(position);
        refreshUI();




        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fav) {
                    fav = true;

                    imgFav.setImageDrawable(getResources().getDrawable(R.drawable.star_checked));
                    insertRealm();

                } else if (fav) {
                    fav = false;

                    imgFav.setImageDrawable(getResources().getDrawable(R.drawable.star_unchecked));
                    removeRealm();
                }


            }
        });

    }



    private void checkFav() {
        if (CustomApplication.moviesListRealm.getMoviesList().size() > 0) {
            for (int i = 0; i < CustomApplication.moviesListRealm.getMoviesList().size(); i++) {
                if (movieRealm.getImdbId().equals(CustomApplication.moviesListRealm.getMoviesList().get(i).getImdbId())) {
                    fav = true;
                    imgFav.setImageDrawable(getResources().getDrawable(R.drawable.star_checked));
                    break;
                }
            }
        }


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FavoriteMovieActivity.this, FavsActivity.class));
        finish();
    }



    private void insertRealm() {
        MovieRealm movieRealmInsert = new MovieRealm();
        movieRealmInsert.setImdbId(movieRealm.getImdbId());
        movieRealmInsert.setTitle(movieRealm.getTitle());
        movieRealmInsert.setPoster(movieRealm.getPoster());
        movieRealmInsert.setYear(movieRealm.getYear());
        movieRealmInsert.setGenre(movieRealm.getGenre());
        movieRealmInsert.setDirector(movieRealm.getDirector());
        movieRealmInsert.setImdbRate(movieRealm.getImdbRate());
        movieRealmInsert.setPlot(movieRealm.getPlot());
        movieRealmInsert.setFav(true);

        MoviesListRealm moviesListRealm = new MoviesListRealm();

        moviesListRealm = CustomApplication.getMoviesListRealm();
        moviesListRealm.addMovie(movieRealm);

        realm.beginTransaction();
        realm.copyToRealm(moviesListRealm);
        realm.commitTransaction();

        CustomApplication.moviesListRealm = moviesListRealm;
    }

    private void removeRealm() {

        MovieRealm movieRealmRemove;
        movieRealmRemove = realm.where(MovieRealm.class).equalTo("imdbId", movieRealm.getImdbId()).findFirst();
        CustomApplication.moviesListRealm.getMoviesList().remove(movieRealmRemove);
        realm.beginTransaction();
        movieRealmRemove.deleteFromRealm();
        realm.commitTransaction();


    }

    private void loadUI() {

        tvTitle = findViewById(R.id.tvTitle);
        tvYear = findViewById(R.id.tvYear);
        tvGenre = findViewById(R.id.tvGenre);
        tvDirector = findViewById(R.id.tvDirector);
        tvRate = findViewById(R.id.tvRate);
        tvPlot = findViewById(R.id.tvPlot);
        imgPoster = findViewById(R.id.imgPoster);
        imgFav = findViewById(R.id.imgFav);

    }

    private void refreshUI() {
        tvTitle.setText(movieRealm.getTitle());
        tvYear.setText(movieRealm.getYear());
        tvGenre.setText(movieRealm.getGenre());
        tvDirector.setText(movieRealm.getDirector());
        tvRate.setText("Avaliação IMDB " + movieRealm.getImdbRate());
        tvPlot.setText(movieRealm.getPlot());
        Picasso.get().load(movieRealm.getPoster()).placeholder(R.drawable.pnf).into(imgPoster);
        checkFav();
    }

}
