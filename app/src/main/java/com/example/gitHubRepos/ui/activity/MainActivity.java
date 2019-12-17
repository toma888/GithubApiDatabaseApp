package com.example.gitHubRepos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitHubRepos.R;
import com.example.gitHubRepos.data.GitHubRepositoryMapper;
import com.example.gitHubRepos.di.AppProvider;
import com.example.gitHubRepos.ui.adapter.RepositoryAdapter;
import com.example.gitHubRepos.ui.model.GitHubRepositoryItem;
import com.example.gitHubRepos.ui.model.InternetConnectionHelper;
import com.example.gitHubRepos.ui.viewModel.RepositoryViewModel;
import com.example.gitHubRepos.ui.viewModel.RepositoryViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RepositoryAdapter.ItemClickListener {

    public static final String REPOSITORY_NAME = "REPOSITORY_NAME";
    public static final String REPOSITORY_ID = "REPOSITORY_ID";

    private RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        AppProvider appProvider = AppProvider.getInstance();

        RepositoryViewModel viewModel = ViewModelProviders.of(this, new RepositoryViewModelFactory(
                appProvider.provideGitHubRepositoryImpl(),
                new GitHubRepositoryMapper(),
                appProvider.provideNetworkGitHubRepository(), new InternetConnectionHelper(this)))
                .get(RepositoryViewModel.class);

        viewModel.getCurrentRepositoryList().observe(this, this::renderRepositories);
    }

    private void renderRepositories(List<GitHubRepositoryItem> gitHubRepositoryList) {
        adapter.setItems(new ArrayList<>(gitHubRepositoryList));
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new RepositoryAdapter(this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        GitHubRepositoryItem gitHubRepositoryItem = (GitHubRepositoryItem) adapter.getItem(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(REPOSITORY_NAME, gitHubRepositoryItem.getName());
        intent.putExtra(REPOSITORY_ID, gitHubRepositoryItem.getId());
        startActivity(intent);
    }
}
