package com.lr.welcomejoinus.Activity.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.lr.welcomejoinus.Activity.FavsActivity;
import com.lr.welcomejoinus.Activity.Search.SearchResult;
import com.lr.welcomejoinus.Application.CustomApplication;
import com.lr.welcomejoinus.Models.Search;
import com.lr.welcomejoinus.R;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements  MVPMain.View{

    EditText edtTitulo;

    Button btnTitulo;
    Button btnMeusFav;

    String searchTitulo;

    TextView tvFilmeNaoEncontrado;

    MVPMain.Presenter presenter = new MainPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadUI();
        presenter.setView(this);
//        if (CustomApplication.moviesListRealm.getMoviesList().size() > 0) Toast.makeText(this, CustomApplication.moviesListRealm.getMoviesList().size(), Toast.LENGTH_SHORT).show();
        btnTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtTitulo.getText().toString().length() >0) {
                    searchTitulo = edtTitulo.getText().toString().trim();
                    if (searchTitulo.length() > 3) {
                        String apikey = "63537a4&";
                       //chamada
                        presenter.getMovies(MainActivity.this, apikey, searchTitulo);
                    } else{
                        edtTitulo.setError("Digite ao menos 3 caracteres");

                    }
                } else{
                    edtTitulo.setError("Digite o nome do filme");
                }
            }
        });

        btnMeusFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavsActivity.class));
            }
        });
    }

    public void getMoviesCallOk(Response<Search> response){

        //                                Toast.makeText(MainActivity.this, "aqui", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
        Log.v("onResponse getMovie", response.body().toString());
        if(response.body().getResponse().equals("False")){
            tvFilmeNaoEncontrado.setVisibility(View.VISIBLE);
        }else{
//                                    Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            CustomApplication.setMovies(response.body().getSearch());
            Intent i = new Intent(MainActivity.this, SearchResult.class);
            CustomApplication.setSearchTerm(searchTitulo);
            startActivity(i);

        }
//                                MoviesResponse mr = response.body();
//                                List<Movie> lista = response.body().getMovies();
//                                Movie m1 = lista.get(1);
    }

    public void getMoviesCallError(Throwable t){
        Log.v("GetMoviesError", t.getMessage());
    }

    private void loadUI() {

        edtTitulo = findViewById(R.id.edtTitulo);
        btnTitulo = findViewById(R.id.btnTitulo);
        btnMeusFav = findViewById(R.id.btnMeusFav);
        tvFilmeNaoEncontrado = findViewById(R.id.tvFilmeNaoEncontrado);
    }
}
