package com.mohit.myassignment.service.repository.network;


import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mohit.myassignment.service.repository.network.paging.NetDataSourceFactory;
import com.mohit.myassignment.service.repository.network.paging.NetPageKeyedDataSource;
import com.mohit.myassignment.service.repository.storge.model.Facts;
import com.mohit.myassignment.service.repository.storge.model.NetworkState;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.mohit.myassignment.Constants.LOADING_PAGE_SIZE;
import static com.mohit.myassignment.Constants.NUMBERS_OF_THREADS;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class Network {

    final private static String TAG = Network.class.getSimpleName();
    final private LiveData<PagedList<Facts>> factsPaged;
    final private LiveData<NetworkState> networkState;

    public Network(NetDataSourceFactory dataSourceFactory, PagedList.BoundaryCallback<Facts> boundaryCallback){
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(LOADING_PAGE_SIZE).setPageSize(LOADING_PAGE_SIZE).build();
        networkState = Transformations.switchMap(dataSourceFactory.getNetworkStatus(),
                (Function<NetPageKeyedDataSource, LiveData<NetworkState>>)
                        NetPageKeyedDataSource::getNetworkState);
        Executor executor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS);
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory, pagedListConfig);
        factsPaged = livePagedListBuilder.
                setFetchExecutor(executor).
                setBoundaryCallback(boundaryCallback).
                build();

    }


    public LiveData<PagedList<Facts>> getPagedFacts(){
        return factsPaged;
    }



    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

}
