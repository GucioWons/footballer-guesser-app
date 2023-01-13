package com.guciowons.footballer_guesser_app.domain.leagues.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.leagues.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.domain.BaseViewModel;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import java.util.List;

public class LeaguesViewModel extends BaseViewModel {
    private LeagueRepository leagueRepository;
    private MutableLiveData<List<League>> leagues;

    public LeaguesViewModel(Application application) {
        super(application);
        leagueRepository = new LeagueRepository(application);
        leagues = leagueRepository.getLeagues();
        error = leagueRepository.getError();
    }

    public MutableLiveData<List<League>> getLeagues(){
        return leagues;
    }
}
