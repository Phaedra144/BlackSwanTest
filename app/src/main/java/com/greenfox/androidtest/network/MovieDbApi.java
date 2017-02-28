package com.greenfox.androidtest.network;

import com.greenfox.androidtest.response.LoadPopularMoviesResponse;
import com.greenfox.androidtest.response.GenreResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDbApi {

    @GET("movie/popular")
    Call<LoadPopularMoviesResponse> getPopularMovies(@Query("page") int page);

    @GET("genre/movie/list")
    Call<GenreResponse> getGenres();



}
