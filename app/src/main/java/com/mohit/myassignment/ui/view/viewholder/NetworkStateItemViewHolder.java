package com.mohit.myassignment.ui.view.viewholder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mohit.myassignment.R;
import com.mohit.myassignment.service.repository.storge.model.NetworkState;


/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

    private final ProgressBar progressBar;
    private final TextView errorMsg;

    public NetworkStateItemViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
        errorMsg = itemView.findViewById(R.id.error_msg);
    }

    /**
     * This method is used for check progressBasr Visibility on NetworkState Status Based
     *
     * @param networkState - networkState
     */
    public void bindView(NetworkState networkState) {
        if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

        if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
            errorMsg.setVisibility(View.VISIBLE);
            errorMsg.setText(networkState.getMsg());
        } else {
            errorMsg.setVisibility(View.GONE);
        }
    }
}