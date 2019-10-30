package com.mohit.myassignment.service.repository.network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mohit.myassignment.Constants.FACTS_ARRAY_LIST_CLASS_TYPE;
import static com.mohit.myassignment.Constants.BASE_URL;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class TheDBAPIClient {

    public static APIInterface getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // create OkHttpClient and register an interceptor
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                // we remove from the response some wrapper tags from our facts rows array
                .registerTypeAdapter(FACTS_ARRAY_LIST_CLASS_TYPE, new JsonDeserializer())
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BASE_URL);

        return builder.build().create(APIInterface.class);
    }
}