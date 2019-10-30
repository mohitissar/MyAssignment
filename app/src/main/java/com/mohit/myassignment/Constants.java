package com.mohit.myassignment;

import com.mohit.myassignment.service.repository.storge.model.Facts;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class Constants {
    // network
    public static final String FACTS_ARRAY_DATA_TAG = "rows";
    public static final Type FACTS_ARRAY_LIST_CLASS_TYPE = (new ArrayList<Facts>()).getClass();
    public static final String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    public static final String GET_FACTS = "facts.json";
    //TODO This for set Pull down load more set static because ai not have total page count
    public static final int LOADING_PAGE_SIZE = 2;
    // DB
    public static final String DATA_BASE_NAME = "Facts.db";
    public static final int NUMBERS_OF_THREADS = 3;
}
