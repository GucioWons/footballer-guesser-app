package com.guciowons.footballer_guesser_app.data.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.requests.players.PlayersRequestManager;
import com.guciowons.footballer_guesser_app.domain.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private MutableLiveData<List<Player>> players = new MutableLiveData<>();
    private List<Player> tempPlayers = new ArrayList<>();
    private RequestQueue requestQueue;
    private Integer id;

    public PlayerRepository(Application application, Integer id){
        requestQueue = Volley.newRequestQueue(application);
        this.id = id;
        getPlayers();
    }

    public MutableLiveData<List<Player>> getAllPlayers(){
        return players;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    private void getPlayers(){
        PlayersRequestManager playersRequestManager = new PlayersRequestManager();
        requestQueue.add(playersRequestManager.getPlayersRequest(PlayerRepository.this, id));
    }

    public void setPlayers(){
        players.postValue(tempPlayers);
    }

    public void addPlayer(Player player){
        tempPlayers.add(player);
    }
}
