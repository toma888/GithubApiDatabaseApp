package com.example.gitHubRepos.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.DiffUtil;

import com.example.gitHubRepos.domain.DisplayableItem;
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter;

public class RepositoryAdapter extends AsyncListDifferDelegationAdapter<DisplayableItem> {

    public RepositoryAdapter(Context context, ItemClickListener clickListener) {
        super(DIFF_CALLBACK);

        delegatesManager.addDelegate(new RepositoryAdapterDelegate(context, clickListener));
    }

    private static final DiffUtil.ItemCallback<DisplayableItem> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<DisplayableItem>() {

        @Override
        public boolean areItemsTheSame(DisplayableItem oldItem, DisplayableItem newItem) {
            return oldItem.getItemId() == newItem.getItemId();
        }

        @Override
        public boolean areContentsTheSame(DisplayableItem oldItem, DisplayableItem newItem) {
            return oldItem.getItemHash() == newItem.getItemHash();
        }
    };

    public DisplayableItem getItem(int id) {
        return getItems().get(id);
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}