package com.example.gitHubRepos.ui;

import android.app.Application;

import com.example.gitHubRepos.di.AppProvider;

public class RepositoryApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppProvider.getInstance().init(getApplicationContext());
    }
}
