package com.guciowons.footballer_guesser_app.data.requests.players;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.data.mappers.JsonToClubMapper;
import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.ui.game.activities.GameActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class PlayersRequestManager {
    public JsonObjectRequest getPlayersRequest(GameActivity activity, Integer leagueId, RequestQueue requestQueue){
        String url = "http://192.168.0.2:8080/footballers/league/" + leagueId;
        return new JsonObjectRequest(Request.Method.GET, url, null,
                response -> getClubs(activity, response, requestQueue),
                error -> showError(error, activity));
    }

    private void getClubs(GameActivity activity, JSONObject response, RequestQueue requestQueue){
        try {
            JSONArray clubs = response.getJSONArray("clubs");
            convertClubs(activity, clubs, requestQueue);
        } catch (JSONException e) {
            //TODO
        }
    }

    private void convertClubs(GameActivity activity, JSONArray clubs, RequestQueue requestQueue){
        for(int i = 0; i< clubs.length(); i++){
            try {
                JsonToClubMapper jsonToClubMapper = new JsonToClubMapper();
                downloadClubCrest(activity, jsonToClubMapper.mapJsonToclub(clubs.getJSONObject(i)), clubs.getJSONObject(i), clubs.length(), requestQueue, i);
            }catch (JSONException e) {
                //TODO
            }
        }
    }

    private void downloadClubCrest(GameActivity activity, Club club, JSONObject clubJson, Integer clubsQuantity,RequestQueue requestQueue, Integer i){
        if(club.getUrl().endsWith(".png")) {
            PngCrestRequestManager pngCrestRequestManager = new PngCrestRequestManager();
            requestQueue.add(pngCrestRequestManager.getPngCrestRequest(activity, club, clubJson, i, clubsQuantity));
        }else if(club.getUrl().endsWith(".svg") && !club.getUrl().endsWith("/63.svg") && !club.getUrl().endsWith("/543.svg")){
            SvgCrestRequestManager svgCrestRequestManager = new SvgCrestRequestManager();
            requestQueue.add(svgCrestRequestManager.getSvgCrestRequest(activity, club, clubJson, i, clubsQuantity));
        }
    }

    private void showError(VolleyError error, GameActivity activity){
        activity.endLoadingDialog();
        String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
        Toast.makeText(activity, body, Toast.LENGTH_SHORT).show();
    }
}
