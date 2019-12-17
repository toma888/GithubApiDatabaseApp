package com.example.gitHubRepos.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.gitHubRepos.R;
import com.example.gitHubRepos.data.DetailGitHubRepositoryMapper;
import com.example.gitHubRepos.di.AppProvider;
import com.example.gitHubRepos.ui.model.DetailGitHubRepository;
import com.example.gitHubRepos.ui.model.InternetConnectionHelper;
import com.example.gitHubRepos.ui.viewModel.DetailRepositoryViewModel;
import com.example.gitHubRepos.ui.viewModel.DetailRepositoryViewModelFactory;

import static com.example.gitHubRepos.ui.activity.MainActivity.REPOSITORY_ID;
import static com.example.gitHubRepos.ui.activity.MainActivity.REPOSITORY_NAME;

public class DetailActivity extends AppCompatActivity {
    private TextView tvStarCount;
    private TextView tvForkCount;
    private TextView tvFullName;
    private TextView tvDescription;
    private TextView tvUrl;
    private TextView tvLanguage;
    private TextView tvWatcherCount;
    private TextView tvContributorCount;

    private String name;
    private long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if ((getIntent().getExtras() != null)) {
            name = getIntent().getStringExtra(REPOSITORY_NAME);
            id = getIntent().getLongExtra(REPOSITORY_ID, 0);
        }
        initView();

        AppProvider appProvider = AppProvider.getInstance();

        DetailRepositoryViewModel viewModel = ViewModelProviders.of(this, new DetailRepositoryViewModelFactory(
                appProvider.provideGitHubRepositoryImpl(),
                new DetailGitHubRepositoryMapper(),
                appProvider.provideNetworkGitHubRepository(), new InternetConnectionHelper(this),
                String.valueOf(id), name))
                .get(DetailRepositoryViewModel.class);

        viewModel.getCurrentGitHubRepository().observe(this, this::renderGitHubRepository);
    }

    private void renderGitHubRepository(DetailGitHubRepository detailGitHubRepository) {
        tvContributorCount.setText(String.valueOf(detailGitHubRepository.getIssuesCount()));
        tvStarCount.setText(String.valueOf(detailGitHubRepository.getStarCount()));
        tvForkCount.setText(String.valueOf(detailGitHubRepository.getForkCount()));
        tvFullName.setText(String.valueOf(detailGitHubRepository.getFullName()));
        tvDescription.setText(String.valueOf(detailGitHubRepository.getDescription()));
        tvUrl.setText(String.valueOf(detailGitHubRepository.getUrl()));
        tvLanguage.setText(String.valueOf(detailGitHubRepository.getLanguage()));
        tvWatcherCount.setText(String.valueOf(detailGitHubRepository.getWatcherCount()));
        tvContributorCount.setText(String.valueOf(detailGitHubRepository.getIssuesCount()));
    }

    private void initView() {
        tvStarCount = findViewById(R.id.tv_star);
        tvForkCount = findViewById(R.id.tv_fork);
        tvFullName = findViewById(R.id.tv_full_name);
        tvDescription = findViewById(R.id.tv_description);
        tvUrl = findViewById(R.id.tv_url);
        tvLanguage = findViewById(R.id.tv_language);
        tvWatcherCount = findViewById(R.id.tv_watcher);
        tvContributorCount = findViewById(R.id.tv_contributor);
    }
}
