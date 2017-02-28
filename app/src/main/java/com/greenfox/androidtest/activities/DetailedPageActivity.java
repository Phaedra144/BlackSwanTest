package com.greenfox.androidtest.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.androidtest.R;
import com.greenfox.androidtest.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailedPageActivity extends Activity {

    Movie movie;
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w342/";
    ImageView movieImage;
    TextView movieTitle, movieAverageRate, genreList, relaseDate, overview;
    Button moreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);

        Bundle bundle = getIntent().getExtras();
        movie = (Movie) bundle.getSerializable("movie");

        movieImage = (ImageView) findViewById(R.id.movieImage);
        movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieAverageRate = (TextView) findViewById(R.id.movieAverageRate);
        genreList = (TextView) findViewById(R.id.genreList);
        relaseDate = (TextView) findViewById(R.id.relaseDate);
        overview = (TextView) findViewById(R.id.overview);
        moreInfo = (Button) findViewById(R.id.moreInfo);


        overview.setText(movie.getOverview());
        movieAverageRate.setText(String.valueOf(movie.getAverageVote()));
        movieTitle.setText(movie.getTitle());
        relaseDate.setText(movie.getReleaseDateText());
        movieImage.setImageBitmap(null);
        Picasso.with(movieImage.getContext()).cancelRequest(movieImage);
        Picasso.with(movieImage.getContext()).load(IMAGE_BASE_URL + movie.getPosterPath()).fit().into(movieImage);
        genreList.setText(String.valueOf(movie.getGenreIdList()));

    }
}
