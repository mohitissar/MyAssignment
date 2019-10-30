package com.mohit.myassignment.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mohit.myassignment.R;
import com.mohit.myassignment.service.repository.storge.model.Facts;
import com.mohit.myassignment.service.repository.storge.model.NetworkState;
import com.mohit.myassignment.ui.listeners.ItemClickListener;
import com.mohit.myassignment.ui.view.viewholder.FactViewHolder;
import com.mohit.myassignment.ui.view.viewholder.NetworkStateItemViewHolder;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class FactsPageListAdapter extends PagedListAdapter<Facts, RecyclerView.ViewHolder> {

    private NetworkState networkState;
    private ItemClickListener itemClickListener;

    public FactsPageListAdapter(ItemClickListener itemClickListener) {
        super(Facts.DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == R.layout.fact_item) {
            View view = layoutInflater.inflate(R.layout.fact_item, parent, false);
            FactViewHolder viewHolder = new FactViewHolder(view, itemClickListener);
            return viewHolder;
        } else if (viewType == R.layout.network_state_item) {
            View view = layoutInflater.inflate(R.layout.network_state_item, parent, false);
            return new NetworkStateItemViewHolder(view);
        } else {
            throw new IllegalArgumentException("unknown view type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.fact_item:
                ((FactViewHolder) holder).bindTo(getItem(position));
                break;
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) holder).bindView(networkState);
                break;
        }
    }

    /**
     * This method check the network state while scroll the list up and down
     *
     */
    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return R.layout.fact_item;
        }
    }

    /**
     * This method get the network state on connection change
     *
     * @param newNetworkState - get the network State
     */
    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }
}
