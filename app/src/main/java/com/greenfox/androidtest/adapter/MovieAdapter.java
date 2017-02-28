package com.greenfox.androidtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.androidtest.activities.DetailedPageActivity;
import com.greenfox.androidtest.R;
import com.greenfox.androidtest.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    //You can use this base url to load images with 342 pixel width
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w342/";

    Movie movie;

    private List<Movie> movieList;



    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.movies_overview_listitem, parent, false);


        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        movie = movieList.get(position);
        holder.overview.setText(movie.getOverview());
        holder.movieAverageRate.setText(String.valueOf(movie.getAverageVote()));
        holder.movieTitle.setText(movie.getTitle());
        holder.relaseDate.setText(movie.getReleaseDateText());
        getImages(holder, movie);
        holder.genreList.setText(String.valueOf(movie.getGenreIdList()));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        TextView movieTitle, movieAverageRate, genreList, relaseDate, overview;
        Button moreInfo;


        public MovieViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movieImage);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            movieAverageRate = (TextView) itemView.findViewById(R.id.movieAverageRate);
            genreList = (TextView) itemView.findViewById(R.id.genreList);
            relaseDate = (TextView) itemView.findViewById(R.id.relaseDate);
            overview = (TextView) itemView.findViewById(R.id.overview);
            moreInfo = (Button) itemView.findViewById(R.id.moreInfo);

            moreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailedPageActivity.class);
                    intent.putExtra("movie", movieList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }

    private void getImages(MovieViewHolder holder, Movie movie) {
        holder.movieImage.setImageBitmap(null);
        Picasso.with(holder.movieImage.getContext()).cancelRequest(holder.movieImage);
        Picasso.with(holder.movieImage.getContext()).load(IMAGE_BASE_URL + movie.getPosterPath()).fit().into(holder.movieImage);
    }



}
