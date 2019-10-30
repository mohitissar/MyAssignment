package com.mohit.myassignment.service.repository.network.api;

import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mohit.myassignment.Constants;
import com.mohit.myassignment.service.repository.storge.model.Facts;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

class JsonDeserializer implements com.google.gson.JsonDeserializer {

    private static String TAG = JsonDeserializer.class.getSimpleName();

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Facts> facts = null;
        try {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray factJsonArray = jsonObject.getAsJsonArray(Constants.FACTS_ARRAY_DATA_TAG);
            facts = new ArrayList<>(factJsonArray.size());
            for (int i = 0; i < factJsonArray.size(); i++) {
                // adding the converted wrapper to our container
                Facts dematerialized = context.deserialize(factJsonArray.get(i), Facts.class);
                facts.add(dematerialized);
            }
        } catch (JsonParseException e) {
            Log.e(TAG, String.format("Could not deserialize Facts element: %s", json.toString()));
        }
        return facts;
    }
}
