package com.lr.welcomejoinus.Activity.MovieDetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class MovieDetails extends AppCompatActivity implements MVPMovieDetails.View {

    int plot = 0;

    List<Movie> movies;
    Movie mv;
    MovieDetailed movie = new MovieDetailed();
    int position;
    TextView tvTitle;
    TextView tvYear;
    TextView tvGenre;
    TextView tvDirector;
    TextView tvRate;
    TextView tvPlot;
    ImageView imgPoster;
    Button btnFullPlot;
    String apikey = "63537a4&";

    ImageView imgFav;
    boolean fav = false;

    MVPMovieDetails.Presenter presenter = new MovieDetailsPresenter();

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
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        if (intent.hasExtra("position")) {
            position = intent.getExtras().getInt("position");
        }
        movies = CustomApplication.getMovies();
        mv = movies.get(position);

        loadUI();
        presenter.setView(this);
        loadMovieDetails();


        btnFullPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plot == 0) {
                    loadFullPlot();
                }
                if (plot == 1) {
                    loadMovieDetails();
                }
            }
        });

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
                if (movie.getImdbID().equals(CustomApplication.moviesListRealm.getMoviesList().get(i).getImdbId())) {
                    fav = true;
                    imgFav.setImageDrawable(getResources().getDrawable(R.drawable.star_checked));
                    break;
                }
            }
        }


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MovieDetails.this, SearchResult.class));
        finish();
    }

    private void loadFullPlot() {
        presenter.getMovieDetailsFull(MovieDetails.this, apikey, mv.getTitle());
    }


    private void loadMovieDetails() {
        presenter.getMovieDetails(MovieDetails.this, apikey, mv.getTitle());

    }

    private void insertRealm() {
        MovieRealm movieRealm = new MovieRealm();
        movieRealm.setImdbId(movie.getImdbID());
        movieRealm.setTitle(movie.getTitle());
        movieRealm.setPoster(movie.getPoster());
        movieRealm.setYear(movie.getYear());
        movieRealm.setGenre(movie.getGenre());
        movieRealm.setDirector(movie.getDirector());
        movieRealm.setImdbRate(movie.getImdbRating());
        movieRealm.setPlot(movie.getPlot());
        movieRealm.setFav(true);

        MoviesListRealm moviesListRealm = new MoviesListRealm();

        moviesListRealm = CustomApplication.getMoviesListRealm();
        moviesListRealm.addMovie(movieRealm);

        realm.beginTransaction();
        realm.copyToRealm(moviesListRealm);
        realm.commitTransaction();

        CustomApplication.moviesListRealm = moviesListRealm;
    }

    private void removeRealm() {

        MovieRealm movieRealm;
        movieRealm = realm.where(MovieRealm.class).equalTo("imdbId", movie.getImdbID()).findFirst();
        CustomApplication.moviesListRealm.getMoviesList().remove(movieRealm);
        realm.beginTransaction();
        movieRealm.deleteFromRealm();
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
        btnFullPlot = findViewById(R.id.btnFullPlot);
        imgFav = findViewById(R.id.imgFav);

    }

    private void refreshUI() {
        tvTitle.setText(movie.getTitle());
        tvYear.setText(movie.getYear());
        tvGenre.setText(movie.getGenre());
        tvDirector.setText(movie.getDirector());
        tvRate.setText("Avaliação IMDB " + movie.getImdbRating());
        tvPlot.setText(movie.getPlot());
        Picasso.get().load(movie.getPoster()).placeholder(R.drawable.pnf).into(imgPoster);
        checkFav();
    }

    @Override
    public void movieDetailsOk(Response<MovieDetailed> response) {
        //                Toast.makeText(MovieDetails.this, "aqui", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
        movie = response.body();
        Log.v("getMovieDetailed", movie.toString());
//                Toast.makeText(MovieDetails.this, movie.getTitle(), Toast.LENGTH_SHORT).show();
        refreshUI();
        btnFullPlot.setText("Full Plot");
        plot = 0;
    }

    @Override
    public void movieDetailsError(Throwable t) {
        Log.v("onFailure getMoviemd", t.getMessage());
    }

    @Override
    public void movieDetailsFullOk(Response<MovieDetailed> response) {
//                Toast.makeText(MovieDetails.this, "aqui", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
        movie = response.body();
        Log.v("getMovieDetailed", movie.toString());
//                Toast.makeText(MovieDetails.this, movie.getTitle(), Toast.LENGTH_SHORT).show();
        refreshUI();
        btnFullPlot.setText("Short Plot");
        plot = 1;


    }

    @Override
    public void movieDetailsFullError(Throwable t) {
        Log.v("onFailure getMovie", t.getMessage());
    }
}
