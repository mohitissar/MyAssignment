package com.mohit.myassignment.service.repository.network.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mohit.myassignment.service.repository.storge.model.Facts;

import rx.subjects.ReplaySubject;

/*
    Responsible for creating the DataSource so we can give it to the PagedList.
 */
public class NetDataSourceFactory extends DataSource.Factory {

    private static final String TAG = NetDataSourceFactory.class.getSimpleName();
    private MutableLiveData<NetPageKeyedDataSource> networkStatus;
    private NetPageKeyedDataSource netPageKeyedDataSource;
    public NetDataSourceFactory() {
        this.networkStatus = new MutableLiveData<>();
        netPageKeyedDataSource = new NetPageKeyedDataSource();
    }

    @Override
    public DataSource create() {
        networkStatus.postValue(netPageKeyedDataSource);
        return netPageKeyedDataSource;
    }

    public MutableLiveData<NetPageKeyedDataSource> getNetworkStatus() {
        return networkStatus;
    }

    public ReplaySubject<Facts> getFacts() {
        return netPageKeyedDataSource.getFacts();
    }
}
