package com.guciowons.footballer_guesser_app.ui.game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.requests.LeaguesRequestManager;
import com.guciowons.footballer_guesser_app.domain.entities.League;
import com.guciowons.footballer_guesser_app.domain.models.LeaguesViewModel;
import com.guciowons.footballer_guesser_app.ui.LandingActivity;
import com.guciowons.footballer_guesser_app.ui.MainActivity;
import com.guciowons.footballer_guesser_app.ui.game.adapters.LeaguesAdapter;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class LeaguesActivity extends AppCompatActivity {
    private LeaguesViewModel leaguesViewModel;
    private LoadingDialog loadingDialog;

    private LeaguesAdapter.RecyclerViewClickListener listener;
    private RecyclerView leaguesRecycler;
    private LeaguesAdapter leaguesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        startLoadingDialog();
        leaguesRecycler = findViewById(R.id.leagues_recycler);
        setUpLeaguesRecycler();
        leaguesViewModel = new ViewModelProvider(this).get(LeaguesViewModel.class);
        leaguesViewModel.getAllLeagues().observe(this, leagues -> {
            leaguesAdapter.setLeagues(leagues);
            endLoadingDialog();
        });
    }

    private void setUpLeaguesRecycler(){
        leaguesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        setOnClickListener();
        leaguesAdapter = new LeaguesAdapter(new ArrayList<>(), listener);
        leaguesRecycler.setAdapter(leaguesAdapter);
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

    public void endLoadingDialog(){
        loadingDialog.dismiss();
    }
}