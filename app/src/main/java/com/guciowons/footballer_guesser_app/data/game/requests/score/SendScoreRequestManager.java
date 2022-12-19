package com.guciowons.footballer_guesser_app.data.game.requests.score;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

public class SendScoreRequestManager {
    public JsonObjectRequest sendScoreRequest(Integer userId, Integer leagueId, Integer points){
        String url = "http://192.168.0.8:8080/scores?userId=" + userId + "&leagueId=" + leagueId + "&points=" + points;
        return new JsonObjectRequest(Request.Method.POST, url , null,
                response -> System.out.println("true"),
                error -> System.out.println("false"));
    }
}
