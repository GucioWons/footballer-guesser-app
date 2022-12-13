package com.guciowons.footballer_guesser_app.domain.scoreboard.request;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guciowons.footballer_guesser_app.data.leagues.mapper.JsonToLeagueMapper;
import com.guciowons.footballer_guesser_app.data.leagues.repository.LeagueRepository;
import com.guciowons.footballer_guesser_app.domain.leagues.entity.League;
import com.guciowons.footballer_guesser_app.domain.scoreboard.view_model.ScoreboardViewModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardLeaguesRequestManager {
    public JsonArrayRequest getLeaguesRequest(ScoreboardViewModel scoreboardViewModel){
        String url = "http://192.168.0.2:8080/leagues";
        return new JsonArrayRequest(Request.Method.GET, url, null,
                response1 -> {
                    List<League> leagues = new ArrayList<>();
                    for(int i = 0; i< response1.length(); i++) {
                        try {
                            League league = JsonToLeagueMapper.mapJsonToLeague(response1.getJSONObject(i));
                            int finalI = i;
                            scoreboardViewModel.getRequestQueue().add(new ImageRequest(league.getUrl(), response2 -> {
                                league.setLogo(response2);
                                System.out.println(finalI + " - " + league.getName());
                                leagues.add(league);
                                if(finalI +1 == response1.length()){
                                    System.out.println("set");
                                    scoreboardViewModel.setLeagues(leagues);
                                }
                            }, 200, 200, ImageView.ScaleType.CENTER, null, error -> {

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
