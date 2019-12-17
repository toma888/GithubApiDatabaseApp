package com.example.gitHubRepos.ui.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gitHubRepos.data.DetailGitHubRepositoryMapper;
import com.example.gitHubRepos.data.network.NetworkGitHubRepository;
import com.example.gitHubRepos.domain.Repository;
import com.example.gitHubRepos.ui.model.InternetConnectionHelper;

public class DetailRepositoryViewModelFactory implements ViewModelProvider.Factory {
    private InternetConnectionHelper internetConnectionHelper;
    private NetworkGitHubRepository networkGitHubRepository;
    private Repository repository;
    private DetailGitHubRepositoryMapper mapper;
    private String repoId;
    private String repoName;

    public DetailRepositoryViewModelFactory(Repository repository, DetailGitHubRepositoryMapper mapper,
                                            NetworkGitHubRepository networkGitHubRepository,
                                            InternetConnectionHelper internetConnectionHelper,
                                            String repoId, String repoName) {
        this.repository = repository;
        this.mapper = mapper;
        this.networkGitHubRepository = networkGitHubRepository;
        this.internetConnectionHelper = internetConnectionHelper;
        this.repoId = repoId;
        this.repoName = repoName;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailRepositoryViewModel(repository, mapper,
                networkGitHubRepository, internetConnectionHelper, repoId, repoName);
    }
}