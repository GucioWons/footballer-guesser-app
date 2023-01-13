package com.guciowons.footballer_guesser_app.data.scoreboard.mappers;

import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.data.models.Score;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToScoreMapper {
    public static Score mapJsonToScore(JSONObject json) throws JSONException {
        return new Score(json.getString("username"), json.getInt("score"));
    }
}
