package com.guciowons.footballer_guesser_app.data.game.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.game.requests.players.PlayersRequestManager;
import com.guciowons.footballer_guesser_app.data.models.player.Player;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

public class PlayerRepository {
    private MutableLiveData<List<Player>> players = new MutableLiveData<>();
    private MutableLiveData<Player> answer = new MutableLiveData<>();
    private List<Player> tempPlayers = new ArrayList<>();
    private RequestQueue requestQueue;
    private Integer id;

    public PlayerRepository(Application application, Integer id){
        requestQueue = Volley.newRequestQueue(application);
        this.id = id;
        fetchPlayers();
    }

    private void fetchPlayers(){
        PlayersRequestManager playersRequestManager = new PlayersRequestManager(PlayerRepository.this);
        requestQueue.add(playersRequestManager.getPlayersRequest(PlayerRepository.this, id));
    }

    public MutableLiveData<List<Player>> getPlayers(){
        return players;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public void setPlayers(List<Player> players){
        this.players.postValue(players);
    }

    public void removePlayer(Player player) {
        tempPlayers = players.getValue();
        if(tempPlayers != null) {
            tempPlayers.remove(player);
        }
        players.setValue(tempPlayers);
    }

    public void setAnswer(Player player){
        answer.postValue(player);
    }

    public MutableLiveData<Player> getAnswer(){
        return answer;
    }
}
