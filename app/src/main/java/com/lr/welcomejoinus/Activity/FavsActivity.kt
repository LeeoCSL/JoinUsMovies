package com.lr.welcomejoinus.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.lr.welcomejoinus.Activity.MovieDetails.MovieDetails
import com.lr.welcomejoinus.Adapter.MoviesAdapter
import com.lr.welcomejoinus.Adapter.MoviesRealmAdapter
import com.lr.welcomejoinus.Application.CustomApplication
import com.lr.welcomejoinus.R
import com.lr.welcomejoinus.RealmFiles.RealmModels.MoviesListRealm

class FavsActivity : AppCompatActivity() {

    lateinit var rvMeusFavoritos: RecyclerView
    lateinit var moviesListRealm: MoviesListRealm

    lateinit var tvNaoAdicionou: TextView
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favs)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        loadUI()


    }

    private fun loadUI() {
        tvNaoAdicionou = findViewById(R.id.tvNaoAdicionou)
        rvMeusFavoritos = findViewById(R.id.rvMeusFavoritos)

        moviesListRealm = CustomApplication.getMoviesListRealm()
        if (moviesListRealm.moviesList.size == 0) {
            tvNaoAdicionou.visibility = View.VISIBLE
        }
        val adapter = MoviesRealmAdapter(moviesListRealm.moviesList, applicationContext)
        adapter.setOnItemClickListener { position ->
            val i = Intent(this@FavsActivity, FavoriteMovieActivity::class.java)
            i.putExtra("position", position)
            startActivity(i)
            finish()
        }
        rvMeusFavoritos.adapter = adapter
        rvMeusFavoritos.layoutManager = LinearLayoutManager(this)
    }
}
