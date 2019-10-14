package com.lr.welcomejoinus.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.lr.welcomejoinus.Activity.MovieDetails.MovieDetails;
import com.lr.welcomejoinus.Adapter.MoviesAdapter;
import com.lr.welcomejoinus.Adapter.MoviesRealmAdapter;
import com.lr.welcomejoinus.Application.CustomApplication;
import com.lr.welcomejoinus.R;
import com.lr.welcomejoinus.RealmFiles.RealmModels.MoviesListRealm;

public class FavsActivity extends AppCompatActivity {

    RecyclerView rvMeusFavoritos;
    MoviesListRealm moviesListRealm;

    TextView tvNaoAdicionou;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return super.onSupportNavigateUp();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadUI();


    }

    private void loadUI() {
        tvNaoAdicionou = findViewById(R.id.tvNaoAdicionou);
        rvMeusFavoritos = findViewById(R.id.rvMeusFavoritos);

        moviesListRealm = CustomApplication.getMoviesListRealm();
        if(moviesListRealm.getMoviesList().size() == 0){
            tvNaoAdicionou.setVisibility(View.VISIBLE);
        }
        MoviesRealmAdapter adapter = new MoviesRealmAdapter(moviesListRealm.getMoviesList(), getApplicationContext());
        adapter.setOnItemClickListener(new MoviesRealmAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent(FavsActivity.this, FavoriteMovieActivity.class);
                i.putExtra("position", position);
                startActivity(i);
                finish();
            }
        });
        rvMeusFavoritos.setAdapter(adapter);
        rvMeusFavoritos.setLayoutManager(new LinearLayoutManager(this));
    }
}
