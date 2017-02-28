package com.greenfox.androidtest.network;

import com.greenfox.androidtest.models.Genre;
import com.greenfox.androidtest.response.GenreResponse;
import com.greenfox.androidtest.response.LoadPopularMoviesResponse;

import retrofit2.Callback;
import retrofit2.Retrofit;

public class MovieDbManager {

    private Retrofit retrofit;
    private MovieDbApi movieDbApi;

    public MovieDbManager(){
        retrofit = RetrofitHelper.initRetrofit();
        movieDbApi = retrofit.create(MovieDbApi.class);
    }

    public void loadPopularMovies(int page, Callback<LoadPopularMoviesResponse> callback) {
        movieDbApi.getPopularMovies(page).enqueue(callback);
    }

    public void getNameOfGenres(Callback<GenreResponse> callback){
        movieDbApi.getGenres().enqueue(callback);
    }
}
