package com.example.gitHubRepos.data.database;

import com.example.gitHubRepos.domain.GitHubRepository;
import com.example.gitHubRepos.domain.Repository;

import java.util.List;

public class GitHubRepositoryImpl implements Repository {
    private GitHubRepositoryDao gitHubRepositoryDao;

    public GitHubRepositoryImpl(GitHubRepositoryDao gitHubRepositoryDao) {
        this.gitHubRepositoryDao = gitHubRepositoryDao;
    }

    @Override
    public void addAll(List<GitHubRepository> gitHubRepositoryList) {
        gitHubRepositoryDao.addAll(gitHubRepositoryList);
    }

    @Override
    public void update(GitHubRepository gitHubRepository, String id) {
        gitHubRepositoryDao.update(gitHubRepository, id);
    }

    @Override
    public GitHubRepository get(String id) {
        return gitHubRepositoryDao.get(id);
    }

    @Override
    public List<GitHubRepository> getAll() {
        return gitHubRepositoryDao.getAll();
    }
}
