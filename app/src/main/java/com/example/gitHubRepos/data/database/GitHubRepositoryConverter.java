package com.example.gitHubRepos.data.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.gitHubRepos.domain.GitHubRepository;

import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_DESCRIPTION;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_FORK_COUNT;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_FULL_NAME;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_ID;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_ISSUES_COUNT;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_LANGUAGE;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_NAME;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_STAR_COUNT;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_URL;
import static com.example.gitHubRepos.data.database.GitHubReposDBHelper.COLUMN_WATCHER_COUNT;

public class GitHubRepositoryConverter extends Converter<GitHubRepository> {
    @Override
    GitHubRepository toEntity(Cursor cursor) {
        String name = cursor.getString(cursor
                .getColumnIndex(COLUMN_NAME));
        long id = cursor.getLong(cursor
                .getColumnIndex(COLUMN_ID));
        int starCount = cursor.getInt(cursor
                .getColumnIndex(COLUMN_STAR_COUNT));
        int forkCount = cursor.getInt(cursor
                .getColumnIndex(COLUMN_FORK_COUNT));
        String fullName = cursor.getString(cursor
                .getColumnIndex(COLUMN_FULL_NAME));
        String description = cursor.getString(cursor
                .getColumnIndex(COLUMN_DESCRIPTION));
        String url = cursor.getString(cursor
                .getColumnIndex(COLUMN_URL));
        String language = cursor.getString(cursor
                .getColumnIndex(COLUMN_LANGUAGE));
        int watcherCount = cursor.getInt(cursor
                .getColumnIndex(COLUMN_WATCHER_COUNT));
        int issuesCount = cursor.getInt(cursor
                .getColumnIndex(COLUMN_ISSUES_COUNT));

        GitHubRepository gitHubRepository = new GitHubRepository(id, name, starCount, forkCount);
        gitHubRepository.setFullName(fullName);
        gitHubRepository.setDescription(description);
        gitHubRepository.setUrl(url);
        gitHubRepository.setLanguage(language);
        gitHubRepository.setWatcherCount(watcherCount);
        gitHubRepository.setIssuesCount(issuesCount);
        return gitHubRepository;
    }

    @Override
    ContentValues fromEntity(GitHubRepository gitHubRepository) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, gitHubRepository.getName());
        contentValues.put(COLUMN_ID, gitHubRepository.getId());
        contentValues.put(COLUMN_STAR_COUNT, gitHubRepository.getStarCount());
        contentValues.put(COLUMN_FORK_COUNT, gitHubRepository.getForkCount());
        contentValues.put(COLUMN_FULL_NAME, gitHubRepository.getFullName());
        contentValues.put(COLUMN_DESCRIPTION, gitHubRepository.getDescription());
        contentValues.put(COLUMN_URL, gitHubRepository.getUrl());
        contentValues.put(COLUMN_LANGUAGE, gitHubRepository.getLanguage());
        contentValues.put(COLUMN_WATCHER_COUNT, gitHubRepository.getWatcherCount());
        contentValues.put(COLUMN_ISSUES_COUNT, gitHubRepository.getIssuesCount());
        return contentValues;
    }
}
