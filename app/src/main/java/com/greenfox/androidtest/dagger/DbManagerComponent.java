package com.greenfox.androidtest.dagger;

import com.greenfox.androidtest.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = DbManagerProvider.class)
public interface DbManagerComponent {
    void inject (MainActivity mainActivity);
}
