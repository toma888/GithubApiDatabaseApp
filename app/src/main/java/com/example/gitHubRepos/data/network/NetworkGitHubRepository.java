package com.example.gitHubRepos.data.network;

import com.example.gitHubRepos.data.network.model.GitHubRepositoryResponse;

import java.util.List;

import io.reactivex.Flowable;

public class NetworkGitHubRepository {
    private GitHubApi gitHubApi;

    public NetworkGitHubRepository(GitHubApi gitHubApi) {
        this.gitHubApi = gitHubApi;
    }

    public Flowable<List<GitHubRepositoryResponse>> getRepoList(String owner){
        return gitHubApi.getRepoList(owner);
    }

    public Flowable<GitHubRepositoryResponse> getDetailRepo(String owner, String repo){
        return gitHubApi.getDetailRepo(owner, repo);
    }
}
