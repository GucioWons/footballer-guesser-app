package com.guciowons.footballer_guesser_app.data.game.requests.players;

import com.guciowons.footballer_guesser_app.data.game.mappers.JsonToPlayerMapper;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.data.models.player.Club;

import org.json.JSONArray;
import org.json.JSONException;

public class CrestRequestManager {
    public void addPlayerToActivity(PlayerRepository repository, Club club, JSONArray playersJson, Integer clubsQuantity, Integer i, Integer j){
        try {
            JsonToPlayerMapper jsonToPlayerMapper = new JsonToPlayerMapper();
            repository.addPlayer(jsonToPlayerMapper.mapJsonToPlayer(playersJson.getJSONObject(j), club));
        } catch (JSONException e) {
            //TODO
        }
        if(i +1 == clubsQuantity && j+1 == playersJson.length()){
            repository.setPlayers();
        }
    }
}
