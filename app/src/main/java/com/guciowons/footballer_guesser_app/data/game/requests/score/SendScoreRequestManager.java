package com.guciowons.footballer_guesser_app.data.game.requests.score;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

public class SendScoreRequestManager {
    public JsonObjectRequest sendScoreRequest(String url){
        return new JsonObjectRequest(Request.Method.POST, url , null,
                response -> System.out.println("true"),
                error -> System.out.println("false"));
    }
}
