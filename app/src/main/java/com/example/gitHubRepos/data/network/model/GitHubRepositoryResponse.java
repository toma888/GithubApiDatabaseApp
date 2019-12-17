package com.example.gitHubRepos.data.network.model;

import com.google.gson.annotations.SerializedName;

public class GitHubRepositoryResponse {

    private long id;
    private String name;

    @SerializedName("stargazers_count")
    private int starCount;

    @SerializedName("forks_count")
    private int forkCount;

    @SerializedName("full_name")
    private String fullName;

    private String description;

    @SerializedName("html_url")
    private String url;

    private String language;

    @SerializedName("subscribers_count")
    private int watcherCount;

    @SerializedName("open_issues_count")
    private int issuesCount;

    public GitHubRepositoryResponse(long id, String name, int starCount, int forkCount) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getWatcherCount() {
        return watcherCount;
    }

    public void setWatcherCount(int watcherCount) {
        this.watcherCount = watcherCount;
    }

    public int getIssuesCount() {
        return issuesCount;
    }

    public void setIssuesCount(int issuesCount) {
        this.issuesCount = issuesCount;
    }
}
