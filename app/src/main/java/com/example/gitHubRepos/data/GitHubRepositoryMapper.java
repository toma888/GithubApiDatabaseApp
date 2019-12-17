package com.example.gitHubRepos.data;

import com.example.gitHubRepos.data.network.model.GitHubRepositoryResponse;
import com.example.gitHubRepos.domain.GitHubRepository;
import com.example.gitHubRepos.ui.model.GitHubRepositoryItem;

import java.util.ArrayList;
import java.util.List;

public class GitHubRepositoryMapper {
    public List<GitHubRepositoryItem> transformForPresentation(List<GitHubRepository> gitHubRepositories) {
        List<GitHubRepositoryItem> gitHubRepositoryList = new ArrayList<>();
        for (GitHubRepository gitHubRepository : gitHubRepositories) {
            GitHubRepositoryItem gitHubRepositoryItem = new GitHubRepositoryItem(
                    gitHubRepository.getId(),
                    gitHubRepository.getName(),
                    gitHubRepository.getStarCount(),
                    gitHubRepository.getForkCount());
            gitHubRepositoryList.add(gitHubRepositoryItem);
        }
        return gitHubRepositoryList;
    }

    public List<GitHubRepository> transformForDatabase(List<GitHubRepositoryResponse> gitHubRepositoryResponses) {
        List<GitHubRepository> gitHubRepositories = new ArrayList<>();
        for (GitHubRepositoryResponse gitHubRepositoryResponse : gitHubRepositoryResponses) {
            GitHubRepository gitHubRepository = new GitHubRepository(
                    gitHubRepositoryResponse.getId(),
                    gitHubRepositoryResponse.getName(),
                    gitHubRepositoryResponse.getStarCount(),
                    gitHubRepositoryResponse.getForkCount());
            gitHubRepository.setDescription(gitHubRepositoryResponse.getDescription());
            gitHubRepository.setIssuesCount(gitHubRepositoryResponse.getIssuesCount());
            gitHubRepository.setLanguage(gitHubRepositoryResponse.getLanguage());
            gitHubRepository.setFullName(gitHubRepositoryResponse.getFullName());
            gitHubRepository.setUrl(gitHubRepositoryResponse.getUrl());
            gitHubRepository.setWatcherCount(gitHubRepositoryResponse.getWatcherCount());
            gitHubRepositories.add(gitHubRepository);
        }
        return gitHubRepositories;
    }
}
