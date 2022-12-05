package com.guciowons.footballer_guesser_app.domain.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.domain.entities.League;

import java.util.List;

public class LeaguesViewModel extends AndroidViewModel {
    private LeagueRepository leagueRepository;
    private MutableLiveData<List<League>> leagues;

    public LeaguesViewModel(@NonNull Application application) {
        super(application);
        leagueRepository = new LeagueRepository(application);
        leagues = leagueRepository.getAllLeagues();
    }

    public MutableLiveData<List<League>> getAllLeagues(){
        return leagues;
    }
}
