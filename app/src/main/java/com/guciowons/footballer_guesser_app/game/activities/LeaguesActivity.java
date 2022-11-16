package com.guciowons.footballer_guesser_app.game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.MainActivity;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.authentication.requests.AuthenticationRequestsManager;
import com.guciowons.footballer_guesser_app.game.entities.League;
import com.guciowons.footballer_guesser_app.game.requests.LeagueRequestManager;
import com.guciowons.footballer_guesser_app.preferences.EncryptedPreferencesGetter;

import org.json.JSONObject;

import java.util.List;

public class LeaguesActivity extends AppCompatActivity {
    private List<League> leagues;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        loadingDialog = new LoadingDialog(LeaguesActivity.this);
        loadingDialog.loadingAlertDialog();
        loadData();
    }

    public void loadData(){
        RequestQueue requestQueue = Volley.newRequestQueue(LeaguesActivity.this);
        LeagueRequestManager leagueRequestManager = new LeagueRequestManager();
        requestQueue.add(leagueRequestManager.getLeaguesRequest(LeaguesActivity.this));
    }

    public void setLeagues(List<League> leagues){
        this.leagues = leagues;
        System.out.println(leagues.get(1).getName());
    }

    public LoadingDialog getLoadingDialog(){
        return loadingDialog;
    }
}