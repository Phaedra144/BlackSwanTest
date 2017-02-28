package com.greenfox.androidtest.response;

import com.google.gson.annotations.SerializedName;
import com.greenfox.androidtest.models.Movie;

import java.io.Serializable;
import java.util.List;

public class LoadPopularMoviesResponse implements Serializable {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> movies;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

