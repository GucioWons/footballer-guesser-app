package com.guciowons.footballer_guesser_app.ui.leagues.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.databinding.ActivityLeaguesBinding;
import com.guciowons.footballer_guesser_app.domain.leagues.view_model.LeaguesViewModel;
import com.guciowons.footballer_guesser_app.ui.game.activity.GameActivity;
import com.guciowons.footballer_guesser_app.ui.loadingdialog.LoadingDialog;
import com.guciowons.footballer_guesser_app.ui.leagues.adapter.LeaguesAdapter;
import com.guciowons.footballer_guesser_app.ui.scoreboard.activity.ScoreboardActivity;

import java.util.ArrayList;

public class LeaguesActivity extends AppCompatActivity {
    private ActivityLeaguesBinding binding;
    private LeaguesViewModel leaguesViewModel;
    private LoadingDialog loadingDialog;

    private LeaguesAdapter.RecyclerViewClickListener listener;
    private LeaguesAdapter leaguesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaguesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setUpButton();
        startLoadingDialog();
        setUpLeaguesRecycler();
        setUpObserver();
    }

    private void setUpButton(){
        binding.topButton.setOnClickListener(view -> goToScoreboard());
    }

    public void goToScoreboard(){
        Intent intent = new Intent(LeaguesActivity.this, ScoreboardActivity.class);
        startActivity(intent);
    }

    private void setUpObserver(){
        leaguesViewModel = new ViewModelProvider(this).get(LeaguesViewModel.class);
        leaguesViewModel.getLeagues().observe(this, leagues -> {
            leaguesAdapter.setLeagues(leagues);
            loadingDialog.dismiss();
        });
    }

    private void setUpLeaguesRecycler(){
        binding.leaguesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        setOnClickListener();
        leaguesAdapter = new LeaguesAdapter(new ArrayList<>(), listener);
        binding.leaguesRecycler.setAdapter(leaguesAdapter);
    }

    private void setOnClickListener() {
        listener = (view, name, id) -> {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("name", name);
            startActivity(intent);
        };
    }

    private void startLoadingDialog(){
        loadingDialog = new LoadingDialog(LeaguesActivity.this);
        loadingDialog.show();
    }
}