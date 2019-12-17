package com.example.gitHubRepos.data.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubApiClient {
    private static GitHubApiClient instance;
    private static final String BASE_URL = "https://api.github.com/";

    private Retrofit retrofit;

    private GitHubApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static GitHubApiClient getInstance() {
        if (instance == null) {
            instance = new GitHubApiClient();
        }
        return instance;
    }

    public GitHubApi getGitHubApi() {
        return retrofit.create(GitHubApi.class);
    }
}
