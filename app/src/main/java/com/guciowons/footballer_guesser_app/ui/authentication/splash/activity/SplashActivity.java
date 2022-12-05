package com.guciowons.footballer_guesser_app.ui.authentication.splash.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.domain.authentication.splash.view_model.SplashViewModel;
import com.guciowons.footballer_guesser_app.ui.authentication.landing.activity.LandingActivity;
import com.guciowons.footballer_guesser_app.ui.leagues.activity.LeaguesActivity;

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