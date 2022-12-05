package com.guciowons.footballer_guesser_app.domain.leagues.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.leagues.repository.LeagueRepository;
import com.guciowons.footballer_guesser_app.domain.leagues.entity.League;

import java.util.List;

public class LeaguesViewModel extends AndroidViewModel {
    private LeagueRepository leagueRepository;
    private MutableLiveData<List<League>> leagues;

    public LeaguesViewModel(@NonNull Application application) {
        super(application);
        leagueRepository = new LeagueRepository(application);
        leagues = leagueRepository.getLeagues();
    }

    public MutableLiveData<List<League>> getLeagues(){
        return leagues;
    }
}
