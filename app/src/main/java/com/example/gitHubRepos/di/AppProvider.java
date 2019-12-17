package com.example.gitHubRepos.di;

import android.content.Context;

import com.example.gitHubRepos.data.database.GitHubReposDBHelper;
import com.example.gitHubRepos.data.database.GitHubRepositoryConverter;
import com.example.gitHubRepos.data.database.GitHubRepositoryDao;
import com.example.gitHubRepos.data.database.GitHubRepositoryImpl;
import com.example.gitHubRepos.data.network.GitHubApiClient;
import com.example.gitHubRepos.data.network.NetworkGitHubRepository;

import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.TABLE_REPOSITORY;

public class AppProvider {
    private Context context;
    private static AppProvider instance = new AppProvider();

    private GitHubReposDBHelper gitHubReposDBHelper;

    public void init(Context appContext) {
        context = appContext;
        gitHubReposDBHelper = new GitHubReposDBHelper(context);
    }

    public static AppProvider getInstance() {
        return instance;
    }

    public GitHubRepositoryImpl provideGitHubRepositoryImpl() {
        GitHubRepositoryDao gitHubRepositoryDao = new GitHubRepositoryDao(gitHubReposDBHelper,
                new GitHubRepositoryConverter(), TABLE_REPOSITORY);
        return new GitHubRepositoryImpl(gitHubRepositoryDao);
    }

    public NetworkGitHubRepository provideNetworkGitHubRepository() {
        return new NetworkGitHubRepository(GitHubApiClient.getInstance().getGitHubApi());
    }

}
