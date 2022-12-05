package com.guciowons.footballer_guesser_app.domain.scoreboard.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.domain.scoreboard.entity.Score;
import com.guciowons.footballer_guesser_app.domain.scoreboard.request.ScoresRequestManager;

import java.util.List;

public class ScoreboardViewModel extends AndroidViewModel {
    private MutableLiveData<List<Score>> scores = new MutableLiveData<>();

    private RequestQueue requestQueue;

    public ScoreboardViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
        fetchScores();
    }

    private void fetchScores(){
        ScoresRequestManager scoresRequestManager = new ScoresRequestManager();
        requestQueue.add(scoresRequestManager.getScores(this));
    }

    public MutableLiveData<List<Score>> getScores(){
        return scores;
    }

    public void setScores(List<Score> newScores){
        scores.setValue(newScores);
    }
}
