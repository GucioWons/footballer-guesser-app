package com.guciowons.footballer_guesser_app.data.game.requests.score;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

public class SendScoreRequestManager {
    //TODO
    public JsonObjectRequest sendScoreRequest(Integer userId, Integer leagueId, Integer points){
        String url = "http://footballerguesserservice-env.eba-iwqz7xzh.eu-central-1.elasticbeanstalk.com/scores?userId=" + userId + "&leagueId=" + leagueId + "&points=" + points;
        return new JsonObjectRequest(Request.Method.POST, url , null,
                response -> System.out.println("true"),
                error -> System.out.println("false"));
    }
}
