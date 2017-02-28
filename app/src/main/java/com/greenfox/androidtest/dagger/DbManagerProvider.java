package com.greenfox.androidtest.dagger;

import com.greenfox.androidtest.network.MovieDbManager;
import dagger.Module;
import dagger.Provides;


@Module
public class DbManagerProvider {

    @Provides
    public MovieDbManager movieDbManager(){
        return new MovieDbManager();
    }
}
