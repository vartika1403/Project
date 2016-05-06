package com.example.user.project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.project.webservice.response.Movie;

import java.util.List;

/**
 * Created by USER on 05-05-2016.
 */
public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private List<Movie> movieList ;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.title.setText("Title " +movie.getTitle());
        holder.rating.setText("Popularity" +movie.getPopularity());
        holder.year.setText("Date" +movie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, rating;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.tvItemName);
            year = (TextView)itemView.findViewById(R.id.tvDate);
            rating = (TextView)itemView.findViewById(R.id.tvRating);
        }
    }
}
