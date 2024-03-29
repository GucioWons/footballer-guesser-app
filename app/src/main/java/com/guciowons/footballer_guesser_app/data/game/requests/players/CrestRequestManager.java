package com.guciowons.footballer_guesser_app.data.game.requests.players;

import android.graphics.Bitmap;

import com.guciowons.footballer_guesser_app.data.game.mappers.JsonToPlayerMapper;
import com.guciowons.footballer_guesser_app.data.models.player.Club;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class CrestRequestManager {
    protected final PlayersRequestManager playersRequestManager;

    public CrestRequestManager(PlayersRequestManager playersRequestManager) {
        this.playersRequestManager = playersRequestManager;
    }

    public void setClubCrest(Bitmap bitmap, Club club, JSONObject clubJson){
        club.setCrest(bitmap);
        try {
            convertPlayers(club, clubJson.getJSONArray("footballers"));
        } catch (JSONException e) {
            playersRequestManager.setError("Cannot get one or more clubs!");
        }
    }

    private void convertPlayers(Club club, JSONArray playersJson){
        for (int j = 0; j < playersJson.length(); j++) {
            addPlayerToActivity(club, playersJson, j);
        }
    }

    private void addPlayerToActivity(Club club, JSONArray playersJson, Integer j) {
        try {
            JsonToPlayerMapper jsonToPlayerMapper = new JsonToPlayerMapper();
            playersRequestManager.addPlayer(jsonToPlayerMapper.mapJsonToPlayer(playersJson.getJSONObject(j), club));
        } catch (JSONException e) {
            playersRequestManager.setError("Cannot get one or more players!");
            playersRequestManager.reducePlayerSize();
        }
        if (playersRequestManager.isPlayersSizeFine()) {
            playersRequestManager.sendPlayers();
        }
    }
}
