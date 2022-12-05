package com.guciowons.footballer_guesser_app.game.requests;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guciowons.footballer_guesser_app.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.game.entities.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PlayersRequestManager {
    public JsonArrayRequest getPlayersRequest(GameActivity activity, Integer leagueId){
        String url = "http://192.168.0.2:8080/footballers/league/" + leagueId;
        return new JsonArrayRequest(Request.Method.GET, url, null,
                response -> sendPlayersToActivity(activity, response),
                error -> showError(error, activity));
    }

    private void sendPlayersToActivity(GameActivity activity, JSONArray response){
        activity.setPlayers(convertResponseToPlayers(response));
        activity.updateAdapter();
        activity.endLoadingDialog();
    }

    private List<Player> convertResponseToPlayers(JSONArray response){
        List<Player> players = new ArrayList<>();
        for(int i = 0; i<response.length(); i++){
            try {
                JSONObject playerJson = response.getJSONObject(i);
                Player player = new Player(playerJson.getString("name"),
                        playerJson.getString("nationality"),
                        playerJson.getInt("number"),
                        playerJson.getString("position"),
                        playerJson.getJSONObject("club").getString("name"),
                        playerJson.getJSONObject("club").getString("shortcut"),
                        playerJson.getJSONObject("club").getJSONObject("league").getString("name"));
                players.add(player);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    private void showError(VolleyError error, GameActivity activity){
        activity.endLoadingDialog();
        String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
        Toast.makeText(activity, body, Toast.LENGTH_SHORT).show();
    }
}
