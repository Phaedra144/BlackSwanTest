package com.greenfox.androidtest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.greenfox.androidtest.R;
import com.greenfox.androidtest.adapter.MovieAdapter;
import com.greenfox.androidtest.dagger.DaggerDbManagerComponent;
import com.greenfox.androidtest.response.LoadPopularMoviesResponse;
import com.greenfox.androidtest.models.Movie;
import com.greenfox.androidtest.network.MovieDbManager;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    @Inject
    MovieDbManager movieDbManager;
    MovieAdapter movieAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerDbManagerComponent.builder().build().inject(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.movieCardList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);

        movieDbManager.loadPopularMovies(1, new Callback<LoadPopularMoviesResponse>() {
            @Override
            public void onResponse(Call<LoadPopularMoviesResponse> call, Response<LoadPopularMoviesResponse> response) {
                LoadPopularMoviesResponse loadPopularMoviesResponse = response.body();
                movieList = new ArrayList<>(loadPopularMoviesResponse.getMovies());
                movieAdapter = new MovieAdapter(movieList);
                recyclerView.setAdapter(movieAdapter);
                movieAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Successful load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoadPopularMoviesResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.profile) {
            Toast.makeText(this, "Your profile would pop up here.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.statistics) {
            Toast.makeText(this, "Statistics go here.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
