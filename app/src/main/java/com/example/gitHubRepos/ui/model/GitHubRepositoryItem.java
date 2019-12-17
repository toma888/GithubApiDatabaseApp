package com.example.gitHubRepos.ui.model;

import com.example.gitHubRepos.domain.DisplayableItem;

public class GitHubRepositoryItem implements DisplayableItem {
    private long id;
    private String name;
    private int starCount;
    private int forkCount;

    public GitHubRepositoryItem(long id, String name, int starCount, int forkCount) {
        this.id = id;
        this.name = name;
        this.starCount = starCount;
        this.forkCount = forkCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public int getForkCount() {
        return forkCount;
    }

    public void setForkCount(int forkCount) {
        this.forkCount = forkCount;
    }

    @Override
    public boolean isRepository() {
        return true;
    }

    @Override
    public long getItemId() {
        return getId();
    }

    @Override
    public int getItemHash() {
        return hashCode();
    }
}
