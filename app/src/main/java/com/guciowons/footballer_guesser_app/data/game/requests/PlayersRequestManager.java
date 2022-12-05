package com.guciowons.footballer_guesser_app.data.game.requests;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.data.game.mappers.JsonToClubMapper;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.domain.game.entities.Club;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlayersRequestManager {
    public JsonObjectRequest getPlayersRequest(PlayerRepository repository, Integer leagueId){
        String url = "http://192.168.0.2:8080/footballers/league/" + leagueId;
        return new JsonObjectRequest(Request.Method.GET, url, null,
                response -> getClubs(repository, response),
                error -> {

                });
    }

    private void getClubs(PlayerRepository repository, JSONObject response){
        try {
            JSONArray clubs = response.getJSONArray("clubs");
            convertClubs(repository, clubs);
        } catch (JSONException e) {
            //TODO
        }
    }

    private void convertClubs(PlayerRepository repository, JSONArray clubs){
        for(int i = 0; i< clubs.length(); i++){
            try {
                JsonToClubMapper jsonToClubMapper = new JsonToClubMapper();
                downloadClubCrest(repository, jsonToClubMapper.mapJsonToclub(clubs.getJSONObject(i)), clubs.getJSONObject(i), clubs.length(), i);
            }catch (JSONException e) {
                //TODO
            }
        }
    }

    private void downloadClubCrest(PlayerRepository repository, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        if(club.getUrl().endsWith(".png")) {
            PngCrestRequestManager pngCrestRequestManager = new PngCrestRequestManager();
            repository.getRequestQueue().add(pngCrestRequestManager.getPngCrestRequest(repository, club, clubJson, i, clubsQuantity));
        }else if(club.getUrl().endsWith(".svg") && !club.getUrl().endsWith("/63.svg") && !club.getUrl().endsWith("/543.svg")){
            SvgCrestRequestManager svgCrestRequestManager = new SvgCrestRequestManager();
            repository.getRequestQueue().add(svgCrestRequestManager.getSvgCrestRequest(repository, club, clubJson, i, clubsQuantity));
        }
    }

//    private void showError(VolleyError error, PlayerRepository repository){
//        activity.endLoadingDialog();
//        String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//        Toast.makeText(activity, body, Toast.LENGTH_SHORT).show();
//    }
}
