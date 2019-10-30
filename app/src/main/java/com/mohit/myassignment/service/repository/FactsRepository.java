package com.mohit.myassignment.service.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.PagedList;

import com.mohit.myassignment.service.repository.network.Network;
import com.mohit.myassignment.service.repository.network.paging.NetDataSourceFactory;
import com.mohit.myassignment.service.repository.storge.Database;
import com.mohit.myassignment.service.repository.storge.model.Facts;
import com.mohit.myassignment.service.repository.storge.model.NetworkState;

import rx.schedulers.Schedulers;

public class FactsRepository {
    private static final String TAG = FactsRepository.class.getSimpleName();
    private static FactsRepository instance;
    final private Network network;
    final private Database database;
    final private MediatorLiveData liveDataMerger;

    private FactsRepository(Context context) {

        NetDataSourceFactory dataSourceFactory = new NetDataSourceFactory();

        network = new Network(dataSourceFactory, boundaryCallback);
        database = Database.getInstance(context.getApplicationContext());
        // when we get new facts from net we set them into the database
        liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(network.getPagedFacts(), value -> {
            liveDataMerger.setValue(value);
            Log.d(TAG, value.toString());
        });

        // save the facts into db
        dataSourceFactory.getFacts().
                observeOn(Schedulers.io()).
                subscribe(facts -> {
                    database.factDao().insertFacts(facts);
                });

    }

    private PagedList.BoundaryCallback<Facts> boundaryCallback = new PagedList.BoundaryCallback<Facts>() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            liveDataMerger.addSource(database.getFacts(), value -> {
                liveDataMerger.setValue(value);
                liveDataMerger.removeSource(database.getFacts());
            });
        }
    };

    public static FactsRepository getInstance(Context context) {
        if (instance == null) {
            instance = new FactsRepository(context);
        }
        return instance;
    }

    public LiveData<PagedList<Facts>> getFacts() {
        return liveDataMerger;
    }

    public LiveData<NetworkState> getNetworkState() {
        return network.getNetworkState();
    }
}
