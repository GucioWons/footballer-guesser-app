package com.guciowons.footballer_guesser_app.data.game.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.BaseRepository;
import com.guciowons.footballer_guesser_app.data.game.requests.players.PlayersRequestManager;
import com.guciowons.footballer_guesser_app.data.models.player.Player;

import java.util.List;

public class PlayerRepository extends BaseRepository {
    private final Integer id;

    private final MutableLiveData<List<Player>> players = new MutableLiveData<>();
    private final MutableLiveData<Player> answer = new MutableLiveData<>();

    public PlayerRepository(Application application, Integer id){
        super(application);
        this.id = id;
        fetchPlayers();
    }

    private void fetchPlayers(){
        PlayersRequestManager playersRequestManager = new PlayersRequestManager(PlayerRepository.this);
        requestQueue.add(playersRequestManager.getPlayersRequest(PlayerRepository.this, id));
    }

    public void removePlayer(Player player) {
        List<Player> tempPlayers = players.getValue();
        if(tempPlayers != null) {
            tempPlayers.remove(player);
        }
        players.setValue(tempPlayers);
    }

    public void setPlayers(List<Player> players){
        this.players.postValue(players);
    }

    public MutableLiveData<List<Player>> getPlayers(){
        return players;
    }

    public void setAnswer(Player player){
        answer.postValue(player);
    }

    public MutableLiveData<Player> getAnswer(){
        return answer;
    }
}
