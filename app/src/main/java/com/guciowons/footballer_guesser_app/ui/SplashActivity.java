package com.guciowons.footballer_guesser_app.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.requests.AuthenticationRequestsManager;
import com.guciowons.footballer_guesser_app.data.preferences.EncryptedPreferencesGetter;
import com.guciowons.footballer_guesser_app.domain.models.SignInViewModel;
import com.guciowons.footballer_guesser_app.domain.models.SplashViewModel;
import com.guciowons.footballer_guesser_app.ui.game.activities.LeaguesActivity;

import org.json.JSONObject;

import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {
    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);

        splashViewModel.getResponse().observe(this, response -> {
            if(response.equals("Success")){
                authenticateUser();
            }else{
                closeSplashScreen();
            }
        });

        splashViewModel.authenticateUser();

        //TODO
        //Cant get encrypted preferences after reinstall
    }

    private void authenticateUser(){
        Intent intent = new Intent(this, LeaguesActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    public void closeSplashScreen() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LandingActivity.class));
            finish();
        }, 2000);
    }
}