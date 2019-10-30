package com.mohit.myassignment.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.mohit.myassignment.service.repository.FactsRepository;
import com.mohit.myassignment.service.repository.storge.model.Facts;
import com.mohit.myassignment.service.repository.storge.model.NetworkState;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class FactsListViewModel extends AndroidViewModel {
    private FactsRepository repository;

    public FactsListViewModel(@NonNull Application application) {
        super(application);
        repository = FactsRepository.getInstance(application);
    }

    /**
     * This method get the Facts list from repository
     *
     */
    public LiveData<PagedList<Facts>> getFacts() {
        return repository.getFacts();
    }

    /**
     * This method get the NetworkState from repository
     *
     */
    public LiveData<NetworkState> getNetworkState() {
        return repository.getNetworkState();
    }
}
