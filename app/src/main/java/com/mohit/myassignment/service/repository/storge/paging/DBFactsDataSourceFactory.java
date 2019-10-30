package com.mohit.myassignment.service.repository.storge.paging;

import androidx.paging.DataSource;
import com.mohit.myassignment.service.repository.storge.FactDao;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class DBFactsDataSourceFactory extends DataSource.Factory {

    private static final String TAG = DBFactsDataSourceFactory.class.getSimpleName();
    private DBFactsPageKeyedDataSource factsPageKeyedDataSource;
    public DBFactsDataSourceFactory(FactDao dao) {
        factsPageKeyedDataSource = new DBFactsPageKeyedDataSource(dao);
    }

    @Override
    public DataSource create() {
        return factsPageKeyedDataSource;
    }
}
