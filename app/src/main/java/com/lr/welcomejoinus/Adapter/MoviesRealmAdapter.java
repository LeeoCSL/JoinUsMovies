package com.lr.welcomejoinus.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.lr.welcomejoinus.Models.Movie;
import com.lr.welcomejoinus.R;
import com.lr.welcomejoinus.RealmFiles.RealmModels.MovieRealm;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesRealmAdapter  extends
        RecyclerView.Adapter<MoviesRealmAdapter.ViewHolder> {
    public Context ctx;

    private MoviesRealmAdapter.ItemClickListener itemClickListener;

    public void setOnItemClickListener(MoviesRealmAdapter.ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvTitle;
        public ImageView imgPoster;





        public ViewHolder(View itemView) {

            super(itemView);

            tvTitle = itemView.findViewById(R.id.list_item_title);
            imgPoster = itemView.findViewById(R.id.list_item_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }

        }
    }

    public interface ItemClickListener {

        void onItemClick(int position);
    }

    private List<MovieRealm> mMovieList;

    // Pass in the contact array into the constructor
    public MoviesRealmAdapter(List<MovieRealm> movieList, Context ctx) {
        mMovieList = movieList;
        this.ctx = ctx;
    }

    @Override
    public MoviesRealmAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.result_item, parent, false);

        // Return a new holder instance
        MoviesRealmAdapter.ViewHolder viewHolder = new MoviesRealmAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MoviesRealmAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        MovieRealm movieList = mMovieList.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.tvTitle;
        textView.setText(movieList.getTitle());
        ImageView imgPoster = viewHolder.imgPoster;

        Picasso.get().load(movieList.getPoster()).placeholder(R.drawable.pnf).into(imgPoster);

    }



    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}

// class MovieList{
//    String title;
//    String poster;
//
//    public MovieList(String title, String poster) {
//        this.title = title;
//        this.poster = poster;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getPoster() {
//        return poster;
//    }
//
//    public void setPoster(String poster) {
//        this.poster = poster;
//    }


//}