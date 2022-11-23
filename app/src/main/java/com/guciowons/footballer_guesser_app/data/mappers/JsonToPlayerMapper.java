package com.guciowons.footballer_guesser_app.data.mappers;

import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.domain.entities.Player;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToPlayerMapper {
    public Player mapJsonToPlayer(JSONObject player, Club club) throws JSONException {
        return new Player(player.getString("name"), player.getString("nationality"), player.getInt("number"), player.getString("position"), club);
    }
}
