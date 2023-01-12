package com.guciowons.footballer_guesser_app.domain.leagues.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.leagues.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import java.util.List;

public class LeaguesViewModel extends AndroidViewModel {
    private LeagueRepository leagueRepository;
    private SharedPreferences account;
    private MutableLiveData<List<League>> leagues;

    public LeaguesViewModel(@NonNull Application application) {
        super(application);
        leagueRepository = new LeagueRepository(application);
        leagues = leagueRepository.getLeagues();
        account = getEncryptedPreferences();
    }

    public void logoutUser(){
        account.edit().clear().apply();
    }

    private SharedPreferences getEncryptedPreferences(){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        return encryptedPreferencesGetter.getEncryptedPreferences(getApplication());
    }

    public MutableLiveData<List<League>> getLeagues(){
        return leagues;
    }
}
