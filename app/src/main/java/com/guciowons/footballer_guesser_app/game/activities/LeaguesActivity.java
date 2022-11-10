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
import com.guciowons.footballer_guesser_app.game.requests.LeagueRequestManager;
import com.guciowons.footballer_guesser_app.preferences.EncryptedPreferencesGetter;

public class LeaguesActivity extends AppCompatActivity {
    TextView id_textview, username_textview, email_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        id_textview = findViewById(R.id.id_textview);
        username_textview = findViewById(R.id.username_textview);
        email_textview = findViewById(R.id.email_textview);
        loadData();
    }

    public void loadData(){
        RequestQueue requestQueue = Volley.newRequestQueue(LeaguesActivity.this);
        LeagueRequestManager leagueRequestManager = new LeagueRequestManager();
        requestQueue.add(leagueRequestManager.getLeaguesRequest(LeaguesActivity.this));
    }
}