package com.guciowons.footballer_guesser_app.data.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.domain.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    private MutableLiveData<List<Player>> history = new MutableLiveData<>();

    public HistoryRepository(Application application){
        history.postValue(new ArrayList<>());
    }

    public void addPlayer(Player player){
        List<Player> newPlayers = history.getValue();
        newPlayers.add(player);
        history.setValue(newPlayers);
    }

    public MutableLiveData<List<Player>> getHistory(){
        return history;
    }
}
