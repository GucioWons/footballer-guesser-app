package com.guciowons.footballer_guesser_app.domain.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.repositories.HistoryRepository;
import com.guciowons.footballer_guesser_app.data.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.domain.entities.Player;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    private PlayerRepository playerRepository;
    private HistoryRepository historyRepository;
    private MutableLiveData<List<Player>> players;
    private MutableLiveData<List<Player>> history;

    public GameViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(getApplication());
        history = historyRepository.getHistory();
    }

    public void fetchPlayers(Integer id){
        playerRepository = new PlayerRepository(getApplication(), id);
        players = playerRepository.getAllPlayers();
    }

    public void addPlayerToHistory(Player player){
        historyRepository.addPlayer(player);
    }

    public MutableLiveData<List<Player>> getHistory(){
        return history;
    }

    public MutableLiveData<List<Player>> getAllPlayers(){
        return players;
    }
}
