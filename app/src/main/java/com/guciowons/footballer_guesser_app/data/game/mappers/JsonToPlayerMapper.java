package com.guciowons.footballer_guesser_app.data.game.mappers;

import com.guciowons.footballer_guesser_app.data.models.player.Club;
import com.guciowons.footballer_guesser_app.data.models.player.Player;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToPlayerMapper {
    public Player mapJsonToPlayer(JSONObject player, Club club) throws JSONException {
        return new Player(player.getString("name"), player.getString("nationality"), player.getInt("number"), player.getString("position"), club);
    }
}
