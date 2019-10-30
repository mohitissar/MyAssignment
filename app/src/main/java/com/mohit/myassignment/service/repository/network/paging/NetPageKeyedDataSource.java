package com.mohit.myassignment.service.repository.network.paging;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.mohit.myassignment.service.repository.network.api.APIInterface;
import com.mohit.myassignment.service.repository.network.api.TheDBAPIClient;
import com.mohit.myassignment.service.repository.storge.model.Facts;
import com.mohit.myassignment.service.repository.storge.model.NetworkState;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subjects.ReplaySubject;

/**
 * Created by Mohit Issar on 10/25/2019.
 *
 * Responsible for loading the data by page
 */

public class NetPageKeyedDataSource extends PageKeyedDataSource<String, Facts> {

    private static final String TAG = NetPageKeyedDataSource.class.getSimpleName();
    private final APIInterface apiInterface;
    private final MutableLiveData networkState;
    private final ReplaySubject<Facts> factsObservable;

    NetPageKeyedDataSource() {
        apiInterface = TheDBAPIClient.getClient();
        networkState = new MutableLiveData();
        factsObservable = ReplaySubject.create();
    }

    public MutableLiveData getNetworkState() {
        return networkState;
    }

    public ReplaySubject<Facts> getFacts() {
        return factsObservable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final PageKeyedDataSource.LoadInitialCallback<String, Facts> callback) {
        Log.i(TAG, "Loading Initial Rang, Count " + params.requestedLoadSize);

        networkState.postValue(NetworkState.LOADING);
        Call<ArrayList<Facts>> callBack = apiInterface.getFacts();
        callBack.enqueue(new Callback<ArrayList<Facts>>() {
            @Override
            public void onResponse(Call<ArrayList<Facts>> call, Response<ArrayList<Facts>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), Integer.toString(1), Integer.toString(2));
                    networkState.postValue(NetworkState.LOADED);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        response.body().forEach(factsObservable::onNext);
                    }
                } else {
                    Log.e("API CALL", response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Facts>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                callback.onResult(new ArrayList<>(), Integer.toString(1), Integer.toString(2));
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, final @NonNull LoadCallback<String, Facts> callback) {
        Log.i(TAG, "Loading page " + params.key );
        networkState.postValue(NetworkState.LOADING);
        final AtomicInteger page = new AtomicInteger(0);
        try {
            page.set(Integer.parseInt(params.key));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        Call<ArrayList<Facts>> callBack = apiInterface.getFacts();
        callBack.enqueue(new Callback<ArrayList<Facts>>() {
            @Override
            public void onResponse(Call<ArrayList<Facts>> call, Response<ArrayList<Facts>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(),Integer.toString(page.get()+1));
                    networkState.postValue(NetworkState.LOADED);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        response.body().forEach(factsObservable::onNext);
                    }
                } else {
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                    Log.e("API CALL", response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Facts>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
                callback.onResult(new ArrayList<>(),Integer.toString(page.get()));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Facts> callback) {
    }
}
