package com.guciowons.footballer_guesser_app.presence.leagues.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;
import com.guciowons.footballer_guesser_app.presence.authorization.landing.activities.LandingActivity;
import com.guciowons.footballer_guesser_app.presence.scoreboard.activities.ScoreboardActivity;
import com.guciowons.footballer_guesser_app.presence.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.databinding.ActivityLeaguesBinding;
import com.guciowons.footballer_guesser_app.domain.leagues.viewmodel.LeaguesViewModel;
import com.guciowons.footballer_guesser_app.presence.loading.dialogs.LoadingDialog;
import com.guciowons.footballer_guesser_app.presence.leagues.adapters.LeaguesAdapter;

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
        setUpMenu();
        startLoadingDialog();
        setUpLeaguesRecycler();
        setUpObserver();
    }

    private void setUpMenu(){
        binding.appBar.toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.scoreboard:
                    goToScoreboard();
                    break;
                case R.id.logout:
                    logoutUser();
                    break;
            }
            return true;
        });
    }

    public void goToScoreboard(){
        Intent intent = new Intent(LeaguesActivity.this, ScoreboardActivity.class);
        startActivity(intent);
    }

    public void logoutUser(){
        leaguesViewModel.logoutUser();
        Intent intent = new Intent(LeaguesActivity.this, LandingActivity.class);
        startActivity(intent);
    }

    private void setUpObserver(){
        leaguesViewModel = new LeaguesViewModel(getApplication());
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