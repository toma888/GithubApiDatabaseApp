package com.example.gitHubRepos.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gitHubRepos.data.DetailGitHubRepositoryMapper;
import com.example.gitHubRepos.data.network.NetworkGitHubRepository;
import com.example.gitHubRepos.domain.Repository;
import com.example.gitHubRepos.ui.model.DetailGitHubRepository;
import com.example.gitHubRepos.ui.model.InternetConnectionHelper;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class DetailRepositoryViewModel extends ViewModel {
    private static final String REPOSITORY_OWNER = "square";

    private final CompositeDisposable disposables = new CompositeDisposable();

    private NetworkGitHubRepository networkGitHubRepository;
    private DetailGitHubRepositoryMapper mapper;
    private Repository repository;
    private String repoId;
    private String repoName;

    private MutableLiveData<DetailGitHubRepository> currentGitHubRepository = new MutableLiveData<>();

    public DetailRepositoryViewModel(Repository repository, DetailGitHubRepositoryMapper mapper,
                                    NetworkGitHubRepository networkGitHubRepository,
                                     InternetConnectionHelper internetConnectionHelper,
                                     String repoId, String repoName) {
        this.repository = repository;
        this.mapper = mapper;
        this.networkGitHubRepository = networkGitHubRepository;
        this.repoId = repoId;
        this.repoName = repoName;

        getRepositoryFromDB();

        if (internetConnectionHelper.isNetworkAvailable()) updateDataBaseFromApi();
    }

    public LiveData<DetailGitHubRepository> getCurrentGitHubRepository() {
        return currentGitHubRepository;
    }

    private void getRepositoryFromDB() {
        disposables.add(Flowable.fromCallable(() ->
                repository.get(repoId))
                .subscribeOn(Schedulers.io())
                .map(gitHubRepository -> mapper.transformForPresentation(gitHubRepository))
                .subscribe(detailGitHubRepository -> currentGitHubRepository.postValue(detailGitHubRepository)));
    }

    public void updateDataBaseFromApi() {
        disposables.add(networkGitHubRepository.getDetailRepo(REPOSITORY_OWNER, repoName)
                .subscribeOn(Schedulers.io())
                .map(gitHubRepositoryResponse -> mapper.transformForDatabase(gitHubRepositoryResponse))
                .doOnNext(gitHubRepository -> repository.update(gitHubRepository, repoId))
                .subscribe(gitHubRepository -> getRepositoryFromDB()));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}