package com.guciowons.footballer_guesser_app.data.leagues.request;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guciowons.footballer_guesser_app.data.leagues.mapper.JsonToLeagueMapper;
import com.guciowons.footballer_guesser_app.data.leagues.repository.LeagueRepository;
import com.guciowons.footballer_guesser_app.domain.leagues.entity.League;

import org.json.JSONException;

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
                                leagues.add(league);
                                if(leagues.size() == response1.length()){
                                    leagueRepository.setLeagues(leagues);
                                }
                            }, 150, 150, ImageView.ScaleType.CENTER, null, error -> {

                            }));
                        } catch (JSONException e) {
                            //TODO
                        }
                    }
                    },
                error -> {

                });
    }
}
