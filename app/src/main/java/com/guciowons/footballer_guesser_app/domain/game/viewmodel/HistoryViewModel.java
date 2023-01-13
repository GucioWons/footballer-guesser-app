package com.guciowons.footballer_guesser_app.domain.game.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.game.repositories.HistoryRepository;
import com.guciowons.footballer_guesser_app.data.models.player.HistoryPlayer;
import com.guciowons.footballer_guesser_app.data.models.player.Player;

import java.util.List;

public class HistoryViewModel {
    private HistoryRepository historyRepository;
    private MutableLiveData<List<HistoryPlayer>> history;

    public HistoryViewModel() {
        historyRepository = new HistoryRepository();
        history = historyRepository.getHistory();
    }

    public void addPlayerToHistory(Player player, HintViewModel hintViewModel){
        historyRepository.addPlayer(new HistoryPlayer(player.getName(), player.getNationality(),
                player.getNumber(), player.getPosition(), player.getClub(),
                player.getNationality().equals(hintViewModel.getCountryHint().getValue()),
                player.getNumber().equals(hintViewModel.getNumberHint().getValue()),
                player.getPosition().equals(hintViewModel.getPositionHint().getValue()),
                player.getClub().equals(hintViewModel.getClubHint().getValue())));
    }

    public int getHistorySize(){
        return history.getValue().size();
    }

    public MutableLiveData<List<HistoryPlayer>> getHistory(){
        return history;
    }
}
