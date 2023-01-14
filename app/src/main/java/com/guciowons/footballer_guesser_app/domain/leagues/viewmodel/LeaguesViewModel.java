package com.guciowons.footballer_guesser_app.domain.leagues.viewmodel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.leagues.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.domain.BaseViewModel;

import java.util.List;

public class LeaguesViewModel extends BaseViewModel {
    private final MutableLiveData<List<League>> leagues;

    public LeaguesViewModel(Application application) {
        super(application);
        LeagueRepository leagueRepository = new LeagueRepository(application);
        leagues = leagueRepository.getLeagues();
        error = leagueRepository.getError();
    }

    public MutableLiveData<List<League>> getLeagues(){
        return leagues;
    }
}
