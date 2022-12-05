package com.guciowons.footballer_guesser_app.ui.scoreboard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.databinding.ActivityLeaguesBinding;
import com.guciowons.footballer_guesser_app.databinding.ActivityScoreboardBinding;
import com.guciowons.footballer_guesser_app.domain.leagues.view_model.LeaguesViewModel;
import com.guciowons.footballer_guesser_app.domain.scoreboard.view_model.ScoreboardViewModel;

public class ScoreboardActivity extends AppCompatActivity {
    private ActivityScoreboardBinding binding;
    private ScoreboardViewModel scoreboardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreboardBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        scoreboardViewModel = new ViewModelProvider(this).get(ScoreboardViewModel.class);
    }
}