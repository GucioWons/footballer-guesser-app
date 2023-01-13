package com.guciowons.footballer_guesser_app.data.game.repositories;


import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.models.player.HistoryPlayer;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    private MutableLiveData<List<HistoryPlayer>> history = new MutableLiveData<>();

    public HistoryRepository(){
        history.postValue(new ArrayList<>());
    }

    public void addPlayer(HistoryPlayer historyPlayer){
        List<HistoryPlayer> newPlayers = history.getValue();
        if (newPlayers != null){
            newPlayers.add(historyPlayer);
        }
        history.setValue(newPlayers);
    }

    public MutableLiveData<List<HistoryPlayer>> getHistory(){
        return history;
    }
}
