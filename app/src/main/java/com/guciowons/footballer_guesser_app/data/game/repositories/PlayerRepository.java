package com.guciowons.footballer_guesser_app.data.game.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.game.requests.PlayersRequestManager;
import com.guciowons.footballer_guesser_app.domain.game.entities.Player;

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
        fetchPlayers();
    }

    private void fetchPlayers(){
        PlayersRequestManager playersRequestManager = new PlayersRequestManager();
        requestQueue.add(playersRequestManager.getPlayersRequest(PlayerRepository.this, id));
    }

    public MutableLiveData<List<Player>> getPlayers(){
        return players;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public void setPlayers(){
        players.postValue(tempPlayers);
    }

    public void addPlayer(Player player){
        tempPlayers.add(player);
    }
}
