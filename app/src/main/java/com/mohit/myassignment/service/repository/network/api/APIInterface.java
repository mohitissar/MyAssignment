package com.mohit.myassignment.service.repository.network.api;

import com.mohit.myassignment.service.repository.storge.model.Facts;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

import static com.mohit.myassignment.Constants.GET_FACTS;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public interface APIInterface {

    @GET(GET_FACTS)
    Call<ArrayList<Facts>> getFacts();
}
