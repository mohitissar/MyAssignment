package com.mohit.myassignment.service.repository.storge;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mohit.myassignment.service.repository.storge.model.Facts;
import com.mohit.myassignment.service.repository.storge.paging.DBFactsDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.mohit.myassignment.Constants.DATA_BASE_NAME;
import static com.mohit.myassignment.Constants.NUMBERS_OF_THREADS;


/**
 * The Room database that contains the Users table
 */
@androidx.room.Database(entities = {Facts.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract FactDao factDao();

    private static final Object sLock = new Object();
    private LiveData<PagedList<Facts>> moviesPaged;

    public static Database getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, DATA_BASE_NAME)
                        .build();
                instance.init();

            }
            return instance;
        }
    }

    private void init() {
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Integer.MAX_VALUE).setPageSize(Integer.MAX_VALUE).build();
        Executor executor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS);
        DBFactsDataSourceFactory dataSourceFactory = new DBFactsDataSourceFactory(factDao());
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory, pagedListConfig);
        moviesPaged = livePagedListBuilder.setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<Facts>> getFacts() {
        return moviesPaged;
    }
}
