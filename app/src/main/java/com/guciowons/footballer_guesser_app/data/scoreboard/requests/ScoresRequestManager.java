package com.guciowons.footballer_guesser_app.data.scoreboard.requests;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guciowons.footballer_guesser_app.data.models.Score;
import com.guciowons.footballer_guesser_app.domain.scoreboard.viewmodel.ScoreboardViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoresRequestManager {
    public JsonArrayRequest getScores(ScoreboardViewModel scoreboardViewModel, String url){
        return new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    List<Score> scores = new ArrayList<>();
                    for(int i = 0; i< response.length(); i++) {
                        try {
                            JSONObject scoreJson = response.getJSONObject(i);
                            scores.add(new Score(scoreJson.getString("username"), scoreJson.getInt("score")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    scoreboardViewModel.setScores(scores.stream().limit(100).collect(Collectors.toList()));
                }, error -> {

                });
    }
}
