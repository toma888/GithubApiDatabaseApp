package com.example.gitHubRepos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitHubRepos.R;
import com.example.gitHubRepos.domain.DisplayableItem;
import com.example.gitHubRepos.ui.model.GitHubRepositoryItem;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;

import java.util.List;

public class RepositoryAdapterDelegate extends AdapterDelegate<List<DisplayableItem>> {

    private LayoutInflater inflater;
    private static RepositoryAdapter.ItemClickListener clickListener;

    public RepositoryAdapterDelegate(Context context, RepositoryAdapter.ItemClickListener clickListener) {
        this.inflater = LayoutInflater.from(context);
        RepositoryAdapterDelegate.clickListener = clickListener;
    }

    @Override
    public boolean isForViewType(@NonNull List<DisplayableItem> items, int position) {
        return items.get(position).isRepository();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new RepositoryViewHolder(inflater.inflate(R.layout.item_repository, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<DisplayableItem> items, int position,
                                 @NonNull RecyclerView.ViewHolder holder, @Nullable List<Object> payloads) {
        RepositoryViewHolder repositoryViewHolder = (RepositoryViewHolder) holder;

        GitHubRepositoryItem itemList = (GitHubRepositoryItem) items.get(position);
        repositoryViewHolder.name.setText(itemList.getName());
        repositoryViewHolder.star.setText(String.valueOf(itemList.getStarCount()));
        repositoryViewHolder.fork.setText(String.valueOf(itemList.getForkCount()));
    }

    static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView star;
        TextView fork;

        RepositoryViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            star = itemView.findViewById(R.id.tv_star);
            fork = itemView.findViewById(R.id.tv_fork);
            itemView.setOnClickListener(v -> performClickItem(itemView));
        }

        private void performClickItem(View itemView) {
            if (clickListener != null) clickListener.onItemClick(itemView, getAdapterPosition());
        }

    }
}