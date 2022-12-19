package com.guciowons.footballer_guesser_app.presence.scoreboard.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.databinding.ActivityScoreboardBinding;
import com.guciowons.footballer_guesser_app.domain.scoreboard.viewmodel.ScoreboardViewModel;
import com.guciowons.footballer_guesser_app.presence.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.presence.scoreboard.adapters.ScoreboardLeaguesAdapter;
import com.guciowons.footballer_guesser_app.presence.scoreboard.adapters.ScoresAdapter;

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
        setUpMenu();
        scoreboardViewModel = new ViewModelProvider(this).get(ScoreboardViewModel.class);
        scoreboardViewModel.getScores().observe(this, scores -> scoresAdapter.setScores(scores));
        scoreboardViewModel.getLeagues().observe(this, leagues -> scoreboardLeaguesAdapter.setLeagues(leagues));
    }

    private void setUpMenu(){
        binding.appBar.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout){
                Toast.makeText(ScoreboardActivity.this, "Logout Successful", Toast.LENGTH_LONG).show();
            }
            return true;
        });
    }

    private void setUpButtons(){
        binding.allTimeButton.setOnClickListener(view -> {
            scoreboardViewModel.setTime(null);
            binding.allTimeButton.setBackgroundTintList(getColorStateList(R.color.green));
            binding.weeklyButton.setBackgroundTintList(getColorStateList(R.color.purple_500));
            binding.monthlyButton.setBackgroundTintList(getColorStateList(R.color.purple_500));

        });
        binding.monthlyButton.setOnClickListener(view ->{
            scoreboardViewModel.setTime("monthly");
            binding.allTimeButton.setBackgroundTintList(getColorStateList(R.color.purple_500));
            binding.weeklyButton.setBackgroundTintList(getColorStateList(R.color.purple_500));
            binding.monthlyButton.setBackgroundTintList(getColorStateList(R.color.green));
        });
        binding.weeklyButton.setOnClickListener(view ->{
            scoreboardViewModel.setTime("weekly");
            binding.allTimeButton.setBackgroundTintList(getColorStateList(R.color.purple_500));
            binding.weeklyButton.setBackgroundTintList(getColorStateList(R.color.green));
            binding.monthlyButton.setBackgroundTintList(getColorStateList(R.color.purple_500));
        });

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

    private void setUpScoresRecycler(){
        binding.scoresRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        scoresAdapter = new ScoresAdapter(new ArrayList<>());
        binding.scoresRecycler.setAdapter(scoresAdapter);
    }

    private void setOnClickListener(){
        listener = (view, id) -> {
            scoreboardViewModel.setLeagueId(id);
            binding.allLeaguesButton.setBackgroundTintList(getColorStateList(R.color.purple_200));
        };
    }
}