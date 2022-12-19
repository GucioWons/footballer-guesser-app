package com.guciowons.footballer_guesser_app.data.leagues.mappers;

import com.guciowons.footballer_guesser_app.data.models.League;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToLeagueMapper {
    public static League mapJsonToLeague(JSONObject json) throws JSONException {
        return new League(json.getInt("id"),
                json.getString("name"),
                json.getString("url"));
    }
}
