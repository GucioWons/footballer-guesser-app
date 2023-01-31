package com.guciowons.footballer_guesser_app.data.scoreboard.requests;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guciowons.footballer_guesser_app.data.models.Score;
import com.guciowons.footballer_guesser_app.data.scoreboard.mappers.JsonToScoreMapper;
import com.guciowons.footballer_guesser_app.domain.scoreboard.viewmodel.ScoreboardViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoresRequestManager {
    private final List<Score> scores = new ArrayList<>();

    public JsonArrayRequest getScores(ScoreboardViewModel viewModel, String url){
        return new JsonArrayRequest(Request.Method.GET, url, null,
                scoresJson -> convertScores(viewModel, scoresJson),
                error -> viewModel.setError("Cannot fetch scores!"));
    }

    private void convertScores(ScoreboardViewModel viewModel, JSONArray scoresJson){
        for(int i = 0; i< scoresJson.length(); i++) {
            convertScore(viewModel, scoresJson, i);
        }
        viewModel.setScores(scores.stream().limit(100).collect(Collectors.toList()));
    }

    private void convertScore(ScoreboardViewModel viewModel, JSONArray scoresJson, int i){
        try {
            scores.add(JsonToScoreMapper.mapJsonToScore(scoresJson.getJSONObject(i)));
        } catch (JSONException e) {
            viewModel.setError("Cannot fetch scores!");
        }
    }
}
