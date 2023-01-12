package com.guciowons.footballer_guesser_app.data.leagues.requests;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guciowons.footballer_guesser_app.data.leagues.mappers.JsonToLeagueMapper;
import com.guciowons.footballer_guesser_app.data.leagues.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.data.models.League;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class LeaguesRequestManager {
    private List<League> leagues;

    public JsonArrayRequest getLeaguesRequest(LeagueRepository leagueRepository){
        String url = "http://footballerguesserservice-env.eba-iwqz7xzh.eu-central-1.elasticbeanstalk.com/leagues";
        return new JsonArrayRequest(Request.Method.GET, url, null,
                leaguesJson -> getLeagues(leagueRepository, leaguesJson),
                error -> leagueRepository.setError("Cannot fetch leagues!"));
    }

    private void getLeagues(LeagueRepository leagueRepository, JSONArray leaguesJson){
        leagues = new ArrayList<>();
        for(int i = 0; i< leaguesJson.length(); i++) {
            try {
                getLeague(leagueRepository, leaguesJson, i);
            } catch (JSONException e) {
                leagueRepository.setError("One or more leagues have not been fetched!");
            }
        }
    }

    private void getLeague(LeagueRepository leagueRepository, JSONArray leaguesJson, Integer i) throws JSONException {
        League league = JsonToLeagueMapper.mapJsonToLeague(leaguesJson.getJSONObject(i));
        leagueRepository.getRequestQueue().add(new ImageRequest(league.getUrl(),
                leagueLogo -> convertLeague(leagueRepository, leaguesJson, league, leagueLogo),
                150, 150, ImageView.ScaleType.CENTER, null,
                error -> leagueRepository.setError("One or more leagues have not been fetched!")));
    }

    private void convertLeague(LeagueRepository leagueRepository, JSONArray leaguesJson, League league, Bitmap leagueLogo){
        league.setLogo(leagueLogo);
        leagues.add(league);
        if(leagues.size() == leaguesJson.length()){
            leagueRepository.setLeagues(leagues);
        }
    }
}
