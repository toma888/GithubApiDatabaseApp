package com.example.gitHubRepos.ui.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gitHubRepos.data.GitHubRepositoryMapper;
import com.example.gitHubRepos.data.network.NetworkGitHubRepository;
import com.example.gitHubRepos.domain.Repository;
import com.example.gitHubRepos.ui.model.InternetConnectionHelper;

public class RepositoryViewModelFactory implements ViewModelProvider.Factory {
    private InternetConnectionHelper internetConnectionHelper;
    private NetworkGitHubRepository networkGitHubRepository;
    private Repository repository;
    private GitHubRepositoryMapper mapper;

    public RepositoryViewModelFactory(Repository repository, GitHubRepositoryMapper mapper,
                                      NetworkGitHubRepository networkGitHubRepository,
                                      InternetConnectionHelper internetConnectionHelper) {
        this.repository = repository;
        this.mapper = mapper;
        this.networkGitHubRepository = networkGitHubRepository;
        this.internetConnectionHelper = internetConnectionHelper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RepositoryViewModel(repository, mapper,
                networkGitHubRepository, internetConnectionHelper);
    }
}