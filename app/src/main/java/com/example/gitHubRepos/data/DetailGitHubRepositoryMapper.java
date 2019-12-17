package com.example.gitHubRepos.data;

import com.example.gitHubRepos.data.network.model.GitHubRepositoryResponse;
import com.example.gitHubRepos.domain.GitHubRepository;
import com.example.gitHubRepos.ui.model.DetailGitHubRepository;

public class DetailGitHubRepositoryMapper {
    public DetailGitHubRepository transformForPresentation(GitHubRepository gitHubRepository) {
        DetailGitHubRepository detailGitHubRepository = new DetailGitHubRepository(
                gitHubRepository.getId(),
                gitHubRepository.getName(),
                gitHubRepository.getStarCount(),
                gitHubRepository.getForkCount());
        detailGitHubRepository.setDescription(gitHubRepository.getDescription());
        detailGitHubRepository.setIssuesCount(gitHubRepository.getIssuesCount());
        detailGitHubRepository.setLanguage(gitHubRepository.getLanguage());
        detailGitHubRepository.setFullName(gitHubRepository.getFullName());
        detailGitHubRepository.setUrl(gitHubRepository.getUrl());
        detailGitHubRepository.setWatcherCount(gitHubRepository.getWatcherCount());
        return detailGitHubRepository;
    }

    public GitHubRepository transformForDatabase(GitHubRepositoryResponse gitHubRepositoryResponse) {
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
        return gitHubRepository;
    }

}
