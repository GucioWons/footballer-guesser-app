package com.guciowons.footballer_guesser_app.game.requests;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;

public class FlagRequestManager {
    public JsonObjectRequest getFlagRequest(String country, ImageView imageView, RequestQueue requestQueue){
        return new JsonObjectRequest(Request.Method.GET, "https://flagcdn.com/en/codes.json", null,
                response -> response.keys().forEachRemaining(key -> {
                    try {
                        if(response.getString(key).equals(country)){
                            ImageRequestManager imageRequestManager = new ImageRequestManager();
                            requestQueue.add(imageRequestManager.getPngRequest("https://flagcdn.com/64x48/" + key + ".png", imageView, 64, 48));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }),
                error -> {});
    }
}
