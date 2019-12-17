package com.example.gitHubRepos.domain;

public interface DisplayableItem {
    boolean isRepository();

    long getItemId();

    int getItemHash();
}
