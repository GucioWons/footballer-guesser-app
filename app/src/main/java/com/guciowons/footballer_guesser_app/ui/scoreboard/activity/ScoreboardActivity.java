package com.guciowons.footballer_guesser_app.ui.scoreboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.databinding.ActivityLeaguesBinding;
import com.guciowons.footballer_guesser_app.databinding.ActivityScoreboardBinding;
import com.guciowons.footballer_guesser_app.domain.leagues.view_model.LeaguesViewModel;
import com.guciowons.footballer_guesser_app.domain.scoreboard.entity.Score;
import com.guciowons.footballer_guesser_app.domain.scoreboard.view_model.ScoreboardViewModel;
import com.guciowons.footballer_guesser_app.ui.leagues.adapter.LeaguesAdapter;
import com.guciowons.footballer_guesser_app.ui.scoreboard.adapter.ScoresAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardActivity extends AppCompatActivity {
    private ActivityScoreboardBinding binding;
    private ScoreboardViewModel scoreboardViewModel;
    private ScoresAdapter scoresAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setUpScoresRecycler();
        setUpButtons();
        scoreboardViewModel = new ViewModelProvider(this).get(ScoreboardViewModel.class);
        scoreboardViewModel.getScores().observe(this, scores -> {
            scoresAdapter.setScores(scores);
        });
    }

    private void setUpButtons(){
        binding.allTimeButton.setOnClickListener(view -> scoreboardViewModel.fetchAllTimeScores());
        binding.monthlyButton.setOnClickListener(view ->scoreboardViewModel.fetchMonthlyScores());
        binding.weeklyButton.setOnClickListener(view ->scoreboardViewModel.fetchWeeklyScores());
    }

    private void setUpScoresRecycler(){
        binding.scoresRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scoresAdapter = new ScoresAdapter(new ArrayList<>());
        binding.scoresRecycler.setAdapter(scoresAdapter);
    }
}