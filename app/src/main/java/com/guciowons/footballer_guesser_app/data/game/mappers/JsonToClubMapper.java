package com.guciowons.footballer_guesser_app.data.game.mappers;

import com.guciowons.footballer_guesser_app.domain.game.entities.Club;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToClubMapper {
    public Club mapJsonToclub(JSONObject club) throws JSONException {
        return new Club(club.getString("name"), club.getString("shortcut"), club.getString("url"));
    }
}
