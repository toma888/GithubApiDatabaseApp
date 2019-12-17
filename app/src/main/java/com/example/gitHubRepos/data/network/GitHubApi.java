package com.example.gitHubRepos.data.network;

import com.example.gitHubRepos.data.network.model.GitHubRepositoryResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("users/{owner}/repos")
    Flowable<List<GitHubRepositoryResponse>> getRepoList(@Path("owner") String owner);

    @GET("repos/{owner}/{repo}")
    Flowable<GitHubRepositoryResponse> getDetailRepo(@Path("owner") String owner,
                                                     @Path("repo") String repo);
}

