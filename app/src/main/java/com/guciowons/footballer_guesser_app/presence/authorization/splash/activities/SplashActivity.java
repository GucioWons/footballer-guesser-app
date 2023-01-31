package com.guciowons.footballer_guesser_app.presence.authorization.splash.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.presence.authorization.BaseAuthActivity;
import com.guciowons.footballer_guesser_app.presence.authorization.landing.activities.LandingActivity;
import com.guciowons.footballer_guesser_app.domain.authorization.splash.viewmodel.SplashViewModel;

public class SplashActivity extends BaseAuthActivity {
    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setUpViewModel();
        splashViewModel.authenticateUser();
    }

    private void setUpViewModel(){
        splashViewModel = new SplashViewModel(getApplication());
        setUpResponseObserver();
    }

    private void setUpResponseObserver(){
        splashViewModel.getResponse().observe(this, response -> {
            if(response.equals("Success")){
                authenticateUser();
            }else{
                closeSplashScreen();
            }
        });
    }

    private void closeSplashScreen() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, LandingActivity.class));
            finish();
        }, 2000);
    }
}