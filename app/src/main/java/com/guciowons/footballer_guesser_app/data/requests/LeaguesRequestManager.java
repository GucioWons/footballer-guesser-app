package com.guciowons.footballer_guesser_app.data.requests;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guciowons.footballer_guesser_app.data.mappers.JsonToLeagueMapper;
import com.guciowons.footballer_guesser_app.data.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.domain.entities.League;
import com.guciowons.footballer_guesser_app.ui.game.activities.LeaguesActivity;

import org.json.JSONException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LeaguesRequestManager {
    public JsonArrayRequest getLeaguesRequest(LeagueRepository leagueRepository){
        String url = "http://192.168.0.2:8080/leagues";
        return new JsonArrayRequest(Request.Method.GET, url, null,
                response1 -> {
                    List<League> leagues = new ArrayList<>();
                    for(int i = 0; i< response1.length(); i++) {
                        try {
                            League league = JsonToLeagueMapper.mapJsonToLeague(response1.getJSONObject(i));
                            int finalI = i;
                            leagueRepository.getRequestQueue().add(new ImageRequest(league.getUrl(), response2 -> {
                                league.setLogo(response2);
                                System.out.println(league.getName());
                                leagues.add(league);
                                if(finalI +1 == response1.length()){
                                    leagueRepository.setLeagues(leagues);
                                }
                            }, 80, 80, ImageView.ScaleType.CENTER, null, error -> {

                            }));
                        } catch (JSONException e) {
                            //TODO
                        }
                    }
                    },
                error -> {

                });
    }

//    private void showError(VolleyError error, LeaguesActivity activity){
////        activity.endLoadingDialog();
//        String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//        Toast.makeText(activity, body, Toast.LENGTH_SHORT).show();
//    }
}
