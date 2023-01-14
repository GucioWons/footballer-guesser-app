package com.guciowons.footballer_guesser_app.data.leagues.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.BaseRepository;
import com.guciowons.footballer_guesser_app.data.leagues.requests.LeaguesRequestManager;
import com.guciowons.footballer_guesser_app.data.models.League;

import java.util.List;

public class LeagueRepository extends BaseRepository {
    private final MutableLiveData<List<League>> leagues = new MutableLiveData<>();

    public LeagueRepository(Application application){
        super(application);
        fetchLeagues();
    }

    private void fetchLeagues(){
        LeaguesRequestManager leaguesRequestManager = new LeaguesRequestManager();
        requestQueue.add(leaguesRequestManager.getLeaguesRequest(LeagueRepository.this));
    }

    public void setLeagues(List<League> leagues){
        this.leagues.postValue(leagues);
    }

    public MutableLiveData<List<League>> getLeagues(){
        return leagues;
    }
}
