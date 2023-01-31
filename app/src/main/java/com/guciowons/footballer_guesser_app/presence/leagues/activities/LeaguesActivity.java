package com.guciowons.footballer_guesser_app.presence.leagues.activities;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.presence.BaseActivity;
import com.guciowons.footballer_guesser_app.presence.scoreboard.activities.ScoreboardActivity;
import com.guciowons.footballer_guesser_app.presence.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.databinding.ActivityLeaguesBinding;
import com.guciowons.footballer_guesser_app.domain.leagues.viewmodel.LeaguesViewModel;
import com.guciowons.footballer_guesser_app.presence.leagues.adapters.LeaguesAdapter;

import java.util.ArrayList;

public class LeaguesActivity extends BaseActivity {
    private ActivityLeaguesBinding binding;
    private LeaguesViewModel leaguesViewModel;
    private LeaguesAdapter.RecyclerViewClickListener listener;
    private LeaguesAdapter leaguesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaguesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        startLoadingDialog();
        setUpViewModel();
        setUpViews();
    }

    private void setUpViewModel(){
        leaguesViewModel = new LeaguesViewModel(getApplication());
        setUpLeaguesObserver();
        setUpErrorObserver(leaguesViewModel);
    }

    private void setUpLeaguesObserver(){
        leaguesViewModel.getLeagues().observe(this, leagues -> {
            leaguesAdapter.setLeagues(leagues);
            loadingDialog.dismiss();
        });
    }

    private void setUpViews(){
        setUpMenu();
        setUpLeaguesRecycler();
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

    private void setUpMenu(){
        binding.appBar.toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.scoreboard){
                goToScoreboard();
            }else if(item.getItemId() == R.id.logout){
                logoutUser(leaguesViewModel);
            }
            return true;
        });
    }

    public void goToScoreboard(){
        Intent intent = new Intent(LeaguesActivity.this, ScoreboardActivity.class);
        startActivity(intent);
    }
}