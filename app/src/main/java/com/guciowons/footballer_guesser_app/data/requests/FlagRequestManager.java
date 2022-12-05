package com.guciowons.footballer_guesser_app.data.requests;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.R;

import org.json.JSONException;
import org.json.JSONObject;

public class FlagRequestManager {
    public JsonObjectRequest getFlagRequest(String country, ImageView imageView, RequestQueue requestQueue){
        return new JsonObjectRequest(Request.Method.GET, "https://flagcdn.com/en/codes.json", null,
                response -> response.keys().forEachRemaining(
                        key -> getFlagImage(response, key, country, requestQueue, imageView)),
                        error -> imageView.setImageBitmap(BitmapFactory.decodeResource(imageView.getResources(), R.drawable.flag)));
    }

    private void getFlagImage(JSONObject response, String key, String country, RequestQueue requestQueue, ImageView imageView){
        try {
            if(response.getString(key).equals(country)){
                ImageRequestManager imageRequestManager = new ImageRequestManager();
                requestQueue.add(imageRequestManager.getPngRequest("https://flagcdn.com/64x48/" + key + ".png", imageView, 64, 48));
            }
        } catch (JSONException e) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(imageView.getResources(), R.drawable.flag));
        }
    }
}
