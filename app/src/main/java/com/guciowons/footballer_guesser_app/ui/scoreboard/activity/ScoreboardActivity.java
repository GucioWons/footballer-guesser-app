package com.guciowons.footballer_guesser_app.ui.scoreboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.databinding.ActivityScoreboardBinding;
import com.guciowons.footballer_guesser_app.domain.scoreboard.view_model.ScoreboardViewModel;
import com.guciowons.footballer_guesser_app.ui.scoreboard.adapter.ScoreboardLeaguesAdapter;
import com.guciowons.footballer_guesser_app.ui.scoreboard.adapter.ScoresAdapter;

import java.util.ArrayList;

public class ScoreboardActivity extends AppCompatActivity {
    private ActivityScoreboardBinding binding;
    private ScoreboardViewModel scoreboardViewModel;
    private ScoresAdapter scoresAdapter;
    private ScoreboardLeaguesAdapter scoreboardLeaguesAdapter;
    private ScoreboardLeaguesAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setUpScoresRecycler();
        setUpScoreboardLeaguesRecycler();
        setUpButtons();
        scoreboardViewModel = new ViewModelProvider(this).get(ScoreboardViewModel.class);
        scoreboardViewModel.getScores().observe(this, scores -> {
            scoresAdapter.setScores(scores);
        });
        scoreboardViewModel.getLeagues().observe(this, leagues ->{
            scoreboardLeaguesAdapter.setLeagues(leagues);
        });
    }

    private void setUpButtons(){
        binding.allTimeButton.setOnClickListener(view -> {
            scoreboardViewModel.setTime(null);
            binding.allTimeButton.setBackgroundColor(getColor(R.color.green));
            binding.weeklyButton.setBackgroundColor(getColor(R.color.purple_500));
            binding.monthlyButton.setBackgroundColor(getColor(R.color.purple_500));

        });
        binding.monthlyButton.setOnClickListener(view ->{
            scoreboardViewModel.setTime("monthly");
            binding.monthlyButton.setBackgroundColor(getColor(R.color.green));
            binding.allTimeButton.setBackgroundColor(getColor(R.color.purple_500));
            binding.weeklyButton.setBackgroundColor(getColor(R.color.purple_500));
        });
        binding.weeklyButton.setOnClickListener(view ->{
            scoreboardViewModel.setTime("weekly");
            binding.weeklyButton.setBackgroundColor(getColor(R.color.green));
            binding.monthlyButton.setBackgroundColor(getColor(R.color.purple_500));
            binding.allTimeButton.setBackgroundColor(getColor(R.color.purple_500));
        });

        binding.allLeaguesButton.setOnClickListener(view ->{
            scoreboardViewModel.setLeagueId(null);
            binding.allLeaguesButton.setBackground(getDrawable(R.color.light_green));
            scoreboardLeaguesAdapter.deleteSelected();
        });
    }

    private void setUpScoreboardLeaguesRecycler(){
        setOnClickListener();
        binding.scoreboardLeaguesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        scoreboardLeaguesAdapter = new ScoreboardLeaguesAdapter(new ArrayList<>(), listener);
        binding.scoreboardLeaguesRecycler.setAdapter(scoreboardLeaguesAdapter);
    }

    private void setUpScoresRecycler(){
        binding.scoresRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scoresAdapter = new ScoresAdapter(new ArrayList<>());
        binding.scoresRecycler.setAdapter(scoresAdapter);
    }

    private void setOnClickListener(){
        listener = (view, id) -> {
            scoreboardViewModel.setLeagueId(id);
            binding.allLeaguesButton.setBackground(getDrawable(R.color.purple_200));
        };
    }
}