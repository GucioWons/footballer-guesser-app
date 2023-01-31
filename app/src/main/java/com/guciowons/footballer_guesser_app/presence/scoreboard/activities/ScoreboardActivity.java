package com.guciowons.footballer_guesser_app.presence.scoreboard.activities;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.databinding.ActivityScoreboardBinding;
import com.guciowons.footballer_guesser_app.domain.scoreboard.viewmodel.ScoreboardViewModel;
import com.guciowons.footballer_guesser_app.presence.BaseActivity;
import com.guciowons.footballer_guesser_app.presence.scoreboard.adapters.ScoreboardLeaguesAdapter;
import com.guciowons.footballer_guesser_app.presence.scoreboard.adapters.ScoresAdapter;

import java.util.ArrayList;

public class ScoreboardActivity extends BaseActivity {
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
        setUpViewModel();
        setUpViews();
    }

    private void setUpViewModel(){
        scoreboardViewModel = new ScoreboardViewModel(getApplication());
        setUpListObservers();
        setUpErrorObserver(scoreboardViewModel);
    }

    private void setUpListObservers(){
        scoreboardViewModel.getScores().observe(this, scores -> scoresAdapter.setScores(scores));
        scoreboardViewModel.getLeagues().observe(this, leagues -> scoreboardLeaguesAdapter.setLeagues(leagues));
    }

    private void setUpViews(){
        setUpMenu(binding.appBar.toolbar, scoreboardViewModel);
        setUpButtons();
        setUpScoreboardLeaguesRecycler();
        setUpScoresRecycler();
    }

    private void setUpButtons(){
        setUpAllTimeButton();
        setUpMonthlyButton();
        setUpWeeklyButton();
        setUpAllLeaguesButton();
    }

    private void setUpAllTimeButton(){
        binding.allTimeButton.setOnClickListener(view -> {
            scoreboardViewModel.setTime(null);
            binding.allTimeButton.setBackgroundTintList(getColorStateList(R.color.green));
            binding.weeklyButton.setBackgroundTintList(getColorStateList(R.color.button));
            binding.monthlyButton.setBackgroundTintList(getColorStateList(R.color.button));

        });
    }

    private void setUpMonthlyButton(){
        binding.monthlyButton.setOnClickListener(view ->{
            scoreboardViewModel.setTime("monthly");
            binding.allTimeButton.setBackgroundTintList(getColorStateList(R.color.button));
            binding.weeklyButton.setBackgroundTintList(getColorStateList(R.color.button));
            binding.monthlyButton.setBackgroundTintList(getColorStateList(R.color.green));
        });
    }

    private void setUpWeeklyButton(){
        binding.weeklyButton.setOnClickListener(view ->{
            scoreboardViewModel.setTime("weekly");
            binding.allTimeButton.setBackgroundTintList(getColorStateList(R.color.button));
            binding.weeklyButton.setBackgroundTintList(getColorStateList(R.color.green));
            binding.monthlyButton.setBackgroundTintList(getColorStateList(R.color.button));
        });
    }

    private void setUpAllLeaguesButton(){
        binding.allLeaguesButton.setOnClickListener(view ->{
            scoreboardViewModel.setLeagueId(null);
            binding.allLeaguesButton.setBackgroundTintList(getColorStateList(R.color.light_green));
            scoreboardLeaguesAdapter.deleteSelected();
        });
    }

    private void setUpScoreboardLeaguesRecycler(){
        setOnClickListener();
        binding.scoreboardLeaguesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        scoreboardLeaguesAdapter = new ScoreboardLeaguesAdapter(new ArrayList<>(), listener);
        binding.scoreboardLeaguesRecycler.setAdapter(scoreboardLeaguesAdapter);
        binding.scoreboardLeaguesRecycler.setNestedScrollingEnabled(false);
    }

    private void setOnClickListener(){
        listener = (view, id) -> {
            scoreboardViewModel.setLeagueId(id);
            binding.allLeaguesButton.setBackgroundTintList(getColorStateList(R.color.button_light));
        };
    }

    private void setUpScoresRecycler(){
        binding.scoresRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scoresAdapter = new ScoresAdapter(new ArrayList<>());
        binding.scoresRecycler.setAdapter(scoresAdapter);
    }
}