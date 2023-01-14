package com.guciowons.footballer_guesser_app.domain.game.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.game.repositories.HistoryRepository;
import com.guciowons.footballer_guesser_app.data.models.player.HistoryPlayer;
import com.guciowons.footballer_guesser_app.data.models.player.Player;

import java.util.List;
import java.util.Objects;

public class HistoryViewModel {
    private final HistoryRepository historyRepository;
    private final MutableLiveData<List<HistoryPlayer>> history;

    public HistoryViewModel() {
        historyRepository = new HistoryRepository();
        history = historyRepository.getHistory();
    }

    public int getHistorySize(){
        return Objects.requireNonNull(history.getValue()).size();
    }

    public void addPlayerToHistory(Player player, HintViewModel hintViewModel){
        historyRepository.addPlayer(new HistoryPlayer(player.getName(), player.getNationality(),
                player.getNumber(), player.getPosition(), player.getClub(),
                player.getNationality().equals(hintViewModel.getCountryHint().getValue()),
                player.getNumber().equals(hintViewModel.getNumberHint().getValue()),
                player.getPosition().equals(hintViewModel.getPositionHint().getValue()),
                player.getClub().equals(hintViewModel.getClubHint().getValue())));
    }

    public MutableLiveData<List<HistoryPlayer>> getHistory(){
        return history;
    }
}
