package com.guciowons.footballer_guesser_app.data.requests.players;

import com.guciowons.footballer_guesser_app.data.mappers.JsonToPlayerMapper;
import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.ui.game.activities.GameActivity;

import org.json.JSONArray;
import org.json.JSONException;

public class CrestRequestManager {
    public void addPlayerToActivity(GameActivity activity, Club club, JSONArray playersJson, Integer clubsQuantity, Integer i, Integer j){
        try {
            JsonToPlayerMapper jsonToPlayerMapper = new JsonToPlayerMapper();
            activity.addPlayer(jsonToPlayerMapper.mapJsonToPlayer(playersJson.getJSONObject(j), club));
        } catch (JSONException e) {
            //TODO
        }
        if(i +1 == clubsQuantity && j+1 == playersJson.length()){
            activity.drawAnswer();
            activity.endLoadingDialog();
        }
    }
}
