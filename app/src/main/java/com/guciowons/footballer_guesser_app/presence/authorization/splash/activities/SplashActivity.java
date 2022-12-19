package com.guciowons.footballer_guesser_app.presence.authorization.splash.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.presence.authorization.landing.activities.LandingActivity;
import com.guciowons.footballer_guesser_app.presence.leagues.activities.LeaguesActivity;
import com.guciowons.footballer_guesser_app.domain.authorization.splash.viewmodel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {
    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setUpObserver();

        splashViewModel.authenticateUser();

        //TODO
        //Cant get encrypted preferences after reinstall
    }

    private void setUpObserver(){
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        splashViewModel.getResponse().observe(this, response -> {
            if(response.equals("Success")){
                authenticateUser();
            }else{
                closeSplashScreen();
            }
        });
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