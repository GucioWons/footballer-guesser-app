package com.guciowons.footballer_guesser_app.domain.scoreboard.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class ScoresRequestManager {
    public JsonArrayRequest getScores(){
        String url = "http://192.168.0.2:8080/scores";
        return new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    System.out.println(response);
                }, error -> {

                });
    }
}
