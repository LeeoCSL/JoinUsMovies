package com.lr.welcomejoinus.Activity.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.lr.welcomejoinus.Activity.MovieDetails.MovieDetails;
import com.lr.welcomejoinus.Adapter.MoviesAdapter;
import com.lr.welcomejoinus.Application.CustomApplication;
import com.lr.welcomejoinus.Models.Movie;
import com.lr.welcomejoinus.Models.Search;
import com.lr.welcomejoinus.R;
import retrofit2.Response;

import java.util.List;

public class SearchResult extends AppCompatActivity implements MVPSearch.View{

    String search;
    String capitalizedSearch = "";
    TextView tvSearched;
    RecyclerView rvFilmes;
    List<Movie> movies;
    int page;

    TextView tvPage;
    Button btnAnt;
    Button btnProx;

    MVPSearch.Presenter presenter = new SearchPresenter();

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        page = 1;
        search = CustomApplication.getSearch();

        String[] palavras = search.split(" ");
        for(int i = 0; i < palavras.length; i++){
            capitalizedSearch = capitalizedSearch + " " + palavras[i].substring(0,1).toUpperCase() + palavras[i].substring(1);
        }
        capitalizedSearch.trim();
//        Intent intent = getIntent();
//        if(intent.hasExtra("termo")){
//            termo = intent.getExtras().getString("termo");
//
//        }

        loadUI();
        presenter.setView(this);


        btnAnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevPagina();
            }
        });
        btnProx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proxPagina();
            }
        });



    }

    public void proxPagina(){
        String apikey = "63537a4&";
        page++;
       presenter.callProxPage(SearchResult.this, apikey, capitalizedSearch, page);
    }

    @Override
    public void callProxPageOk(Response<Search> response) {
        Toast.makeText(SearchResult.this, "aqui", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
        if(!response.body().getResponse().equals("False")){

            Log.v("onResponse getMovie", response.body().toString());

            CustomApplication.setMovies(response.body().getSearch());
            loadUI();
        }


//                                MoviesResponse mr = response.body();
//                                List<Movie> lista = response.body().getMovies();
//                                Movie m1 = lista.get(1);
    }

    @Override
    public void callProxPageError(Throwable t) {
        Log.v("onFailure getMovie", t.getMessage());

    }

    public void prevPagina(){
//        if(page > 1) {
            String apikey = "63537a4&";
            page--;
            presenter.callPrevPage(SearchResult.this, apikey, capitalizedSearch, page);
        }


@Override
public void callPrevPageOk(Response<Search> response) {
    Toast.makeText(SearchResult.this, "aqui", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
    Log.v("onResponse getMovie", response.body().toString());

    CustomApplication.setMovies(response.body().getSearch());
    loadUI();

//                                MoviesResponse mr = response.body();
//                                List<Movie> lista = response.body().getMovies();
//                                Movie m1 = lista.get(1);
}

    @Override
    public void callPrevPageError(Throwable t) {
        Log.v("onFailure getMovie", t.getMessage());
    }

    private void loadUI() {
        movies = CustomApplication.getMovies();
        tvSearched = findViewById(R.id.tvSearched);

        tvSearched.setText(capitalizedSearch);
        rvFilmes = findViewById(R.id.rvFilmes);
        tvPage = findViewById(R.id.tvPage);
        tvPage.setText(""+page);
        btnAnt = findViewById(R.id.btnAnt);
        btnProx = findViewById(R.id.btnProx);




        MoviesAdapter adapter = new MoviesAdapter(movies, getApplicationContext());
        adapter.setOnItemClickListener(new MoviesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent(SearchResult.this, MovieDetails.class);
                i.putExtra("position", position);
                startActivity(i);
                finish();
            }
        });
        rvFilmes.setAdapter(adapter);
        rvFilmes.setLayoutManager(new LinearLayoutManager(this));

        if (page == 1){
            btnAnt.setVisibility(View.GONE);
        }else{
            btnAnt.setVisibility(View.VISIBLE);
        }
    }


}
