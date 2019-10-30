package com.mohit.myassignment.service.repository.storge.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.mohit.myassignment.service.repository.storge.FactDao;
import com.mohit.myassignment.service.repository.storge.model.Facts;

import java.util.List;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class DBFactsPageKeyedDataSource extends PageKeyedDataSource<String, Facts> {

    public static final String TAG = DBFactsPageKeyedDataSource.class.getSimpleName();
    private final FactDao factDao;
    public DBFactsPageKeyedDataSource(FactDao dao) {
        factDao = dao;
    }

    @Override
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<String> params, @NonNull final LoadInitialCallback<String, Facts> callback) {
        Log.i(TAG, "Loading Initial Rang, Count " + params.requestedLoadSize);
        List<Facts> facts = factDao.getFacts();
        if(facts.size() != 0) {
            callback.onResult(facts, "0", "1");
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, final @NonNull LoadCallback<String, Facts> callback) {
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Facts> callback) {
    }
}
