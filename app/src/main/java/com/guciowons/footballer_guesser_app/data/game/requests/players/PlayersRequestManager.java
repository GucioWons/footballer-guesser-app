package com.guciowons.footballer_guesser_app.data.game.requests.players;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.data.game.mappers.JsonToClubMapper;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.data.models.player.Club;
import com.guciowons.footballer_guesser_app.data.models.player.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

public class PlayersRequestManager {
    private final PlayerRepository repository;
    private List<Player> players = new ArrayList<>();
    private int estimatedPlayers = 0;

    public PlayersRequestManager(PlayerRepository repository) {
        this.repository = repository;
    }

    public JsonObjectRequest getPlayersRequest(PlayerRepository repository, Integer leagueId){
        String url = "http://footballerguesserservice-env.eba-iwqz7xzh.eu-central-1.elasticbeanstalk.com/footballers/league/" + leagueId;
        return new JsonObjectRequest(Request.Method.GET, url, null,
                response -> getClubs(repository, response),
                error -> setError("Can't get players!"));
    }

    private void getClubs(PlayerRepository repository, JSONObject response){
        try {
            JSONArray clubsJson = response.getJSONArray("clubs");
            countPlayers(clubsJson);
            convertClubs(repository, clubsJson);
        } catch (JSONException e) {
            setError("Can't get players!");
        }
    }

    private void countPlayers(JSONArray clubsJson){
        for(int i = 0; i< clubsJson.length(); i++){
            try {
                estimatedPlayers = estimatedPlayers + clubsJson.getJSONObject(i).getJSONArray("footballers").length();
            }catch (JSONException e) {
                setError("Can't get players!");
            }
        }
    }

    private void convertClubs(PlayerRepository repository, JSONArray clubsJson){
        for(int i = 0; i< clubsJson.length(); i++){
            try {
                JsonToClubMapper jsonToClubMapper = new JsonToClubMapper();
                downloadClubCrest(repository, jsonToClubMapper.mapJsonToclub(clubsJson.getJSONObject(i)), clubsJson.getJSONObject(i));
            }catch (JSONException e) {
                setError("Can't get players!");
            }
        }
    }

    private void downloadClubCrest(PlayerRepository repository, Club club, JSONObject clubJson){
        if(club.getUrl().endsWith(".png")) {
            PngCrestRequestManager pngCrestRequestManager = new PngCrestRequestManager(PlayersRequestManager.this);
            repository.getRequestQueue().add(pngCrestRequestManager.getPngCrestRequest(club, clubJson));
        }else if(club.getUrl().endsWith("/63.svg") || club.getUrl().endsWith("/543.svg")){
            club.setUrl(club.getUrl().replace("svg", "png"));
            PngCrestRequestManager pngCrestRequestManager = new PngCrestRequestManager(PlayersRequestManager.this);
            repository.getRequestQueue().add(pngCrestRequestManager.getPngCrestRequest(club, clubJson));
        }else{
            SvgCrestRequestManager svgCrestRequestManager = new SvgCrestRequestManager(PlayersRequestManager.this);
            repository.getRequestQueue().add(svgCrestRequestManager.getSvgCrestRequest(club, clubJson));
        }
    }

    public boolean isPlayersSizeFine(){
        return players.size() == estimatedPlayers;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void sendPlayers(){
        repository.setPlayers(players);
        repository.setAnswer(getRandomPlayer());
    }

    public Player getRandomPlayer(){
        XoRoShiRo128PlusRandom xoroRandom = new XoRoShiRo128PlusRandom();
        Player player = players.get(xoroRandom.nextInt(players.size()));
        System.out.println(player.getName());
        return player;
    }

    private void setError(String s) {
    }
}
