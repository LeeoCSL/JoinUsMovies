package com.lr.welcomejoinus.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.lr.welcomejoinus.Activity.Main.MainActivity
import com.lr.welcomejoinus.Activity.MovieDetails.MovieDetails
import com.lr.welcomejoinus.Activity.Search.SearchResult
import com.lr.welcomejoinus.Application.CustomApplication
import com.lr.welcomejoinus.Models.Movie
import com.lr.welcomejoinus.Models.MovieDetailed
import com.lr.welcomejoinus.R
import com.lr.welcomejoinus.RealmFiles.RealmModels.MovieRealm
import com.lr.welcomejoinus.RealmFiles.RealmModels.MoviesListRealm
import com.squareup.picasso.Picasso
import io.realm.Realm
import retrofit2.Response

class FavoriteMovieActivity : AppCompatActivity() {


      var moviesListRealm = CustomApplication.getMoviesListRealm()
      var movieRealm: MovieRealm? = null
      var position: Int = 0
     lateinit var tvTitle: TextView
     lateinit var tvYear: TextView
     lateinit var tvGenre: TextView
     lateinit var tvDirector: TextView
     lateinit var tvRate: TextView
     lateinit var tvPlot: TextView
     lateinit var imgPoster: ImageView
     var apikey = "63537a4&"

     lateinit var imgFav: ImageView
     var fav = false

    //    RealmConfiguration realmConfig = new RealmConfiguration
    //            .Builder(this).build();
    //    Realm realm = Realm.getInstance(realmConfig);
    internal var realm = Realm.getDefaultInstance()

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movie)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val intent = intent
        if (intent.hasExtra("position")) {
            position = intent.extras!!.getInt("position")
        }


        loadUI()
        movieRealm = moviesListRealm.moviesList[position]
        refreshUI()




        imgFav.setOnClickListener {
            if (!fav) {
                fav = true

                imgFav.setImageDrawable(resources.getDrawable(R.drawable.star_checked))
                insertRealm()

            } else if (fav) {
                fav = false

                imgFav.setImageDrawable(resources.getDrawable(R.drawable.star_unchecked))
                removeRealm()
            }
        }

    }


    private fun checkFav() {
        if (CustomApplication.moviesListRealm.moviesList.size > 0) {
            for (i in 0 until CustomApplication.moviesListRealm.moviesList.size) {
                if (movieRealm!!.imdbId == CustomApplication.moviesListRealm.moviesList[i]!!.imdbId) {
                    fav = true
                    imgFav.setImageDrawable(resources.getDrawable(R.drawable.star_checked))
                    break
                }
            }
        }


    }

    override fun onBackPressed() {
        startActivity(Intent(this@FavoriteMovieActivity, FavsActivity::class.java))
        finish()
    }


    private fun insertRealm() {
        val movieRealmInsert = MovieRealm()
        movieRealmInsert.imdbId = movieRealm!!.imdbId
        movieRealmInsert.title = movieRealm!!.title
        movieRealmInsert.poster = movieRealm!!.poster
        movieRealmInsert.year = movieRealm!!.year
        movieRealmInsert.genre = movieRealm!!.genre
        movieRealmInsert.director = movieRealm!!.director
        movieRealmInsert.imdbRate = movieRealm!!.imdbRate
        movieRealmInsert.plot = movieRealm!!.plot
        movieRealmInsert.isFav = true

        var moviesListRealm = MoviesListRealm()

        moviesListRealm = CustomApplication.getMoviesListRealm()
        moviesListRealm.addMovie(movieRealm)

        realm.beginTransaction()
        realm.copyToRealm(moviesListRealm)
        realm.commitTransaction()

        CustomApplication.moviesListRealm = moviesListRealm
    }

    private fun removeRealm() {

        val movieRealmRemove: MovieRealm?
        movieRealmRemove = realm.where(MovieRealm::class.java).equalTo("imdbId", movieRealm!!.imdbId).findFirst()
        CustomApplication.moviesListRealm.moviesList.remove(movieRealmRemove)
        realm.beginTransaction()
        movieRealmRemove!!.deleteFromRealm()
        realm.commitTransaction()


    }

    private fun loadUI() {

        tvTitle = findViewById(R.id.tvTitle)
        tvYear = findViewById(R.id.tvYear)
        tvGenre = findViewById(R.id.tvGenre)
        tvDirector = findViewById(R.id.tvDirector)
        tvRate = findViewById(R.id.tvRate)
        tvPlot = findViewById(R.id.tvPlot)
        imgPoster = findViewById(R.id.imgPoster)
        imgFav = findViewById(R.id.imgFav)

    }

    private fun refreshUI() {
        tvTitle.text = movieRealm!!.title
        tvYear.text = movieRealm!!.year
        tvGenre.text = movieRealm!!.genre
        tvDirector.text = movieRealm!!.director
        tvRate.text = "Avaliação IMDB " + movieRealm!!.imdbRate
        tvPlot.text = movieRealm!!.plot
        Picasso.get().load(movieRealm!!.poster).placeholder(R.drawable.pnf).into(imgPoster)
        checkFav()
    }

}
