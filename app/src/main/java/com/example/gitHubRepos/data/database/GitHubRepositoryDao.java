package com.example.gitHubRepos.data.database;

import com.example.gitHubRepos.domain.GitHubRepository;

public class GitHubRepositoryDao extends BaseDao<GitHubRepository> {

    public GitHubRepositoryDao(GitHubReposDBHelper gitHubReposDBHelper,
                               Converter<GitHubRepository> converter, String tableName) {
        super(gitHubReposDBHelper, converter, tableName);
    }
}
