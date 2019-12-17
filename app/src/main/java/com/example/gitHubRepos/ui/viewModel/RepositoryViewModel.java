package com.example.gitHubRepos.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gitHubRepos.data.GitHubRepositoryMapper;
import com.example.gitHubRepos.data.network.NetworkGitHubRepository;
import com.example.gitHubRepos.domain.Repository;
import com.example.gitHubRepos.ui.model.GitHubRepositoryItem;
import com.example.gitHubRepos.ui.model.InternetConnectionHelper;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class RepositoryViewModel extends ViewModel {
    private static final String REPOSITORY_OWNER = "square";

    private final CompositeDisposable disposables = new CompositeDisposable();

    private NetworkGitHubRepository networkGitHubRepository;
    private GitHubRepositoryMapper mapper;
    private Repository repository;

    private MutableLiveData<List<GitHubRepositoryItem>> currentRepositoryList = new MutableLiveData<>();

    public RepositoryViewModel(Repository repository, GitHubRepositoryMapper mapper,
                               NetworkGitHubRepository networkGitHubRepository,
                               InternetConnectionHelper internetConnectionHelper) {
        this.repository = repository;
        this.mapper = mapper;
        this.networkGitHubRepository = networkGitHubRepository;

        getRepositoryListFromDB();

        if (internetConnectionHelper.isNetworkAvailable()) updateDataBaseFromApi();
    }

    public LiveData<List<GitHubRepositoryItem>> getCurrentRepositoryList() {
        return currentRepositoryList;
    }

    private void getRepositoryListFromDB() {
        disposables.add(Flowable.fromCallable(() ->
                repository.getAll())
                .subscribeOn(Schedulers.io())
                .map(gitHubRepositories -> mapper.transformForPresentation(gitHubRepositories))
                .subscribe(gitHubRepositoryItems -> currentRepositoryList.postValue(gitHubRepositoryItems)));
    }

    public void updateDataBaseFromApi() {
        disposables.add(networkGitHubRepository.getRepoList(REPOSITORY_OWNER)
                .subscribeOn(Schedulers.io())
                .map(gitHubRepositoryResponses -> mapper.transformForDatabase(gitHubRepositoryResponses))
                .doOnNext(gitHubRepositories -> repository.addAll(gitHubRepositories))
                .subscribe(gitHubRepositories -> getRepositoryListFromDB()));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}