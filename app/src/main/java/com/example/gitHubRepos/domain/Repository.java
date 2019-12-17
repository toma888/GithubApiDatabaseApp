package com.example.gitHubRepos.domain;

import java.util.List;

public interface Repository {
    void addAll(List<GitHubRepository> gitHubRepositoryList);

    void update(GitHubRepository gitHubRepository, String id);

    GitHubRepository get(String id);

    List<GitHubRepository> getAll();
}
