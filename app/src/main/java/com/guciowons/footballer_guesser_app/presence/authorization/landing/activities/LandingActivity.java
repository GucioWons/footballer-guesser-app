package com.guciowons.footballer_guesser_app.presence.authorization.landing.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.presence.authorization.sign_in.activities.SignInActivity;
import com.guciowons.footballer_guesser_app.presence.authorization.sign_up.activities.SignUpActivity;
import com.guciowons.footballer_guesser_app.databinding.ActivityLandingBinding;

public class LandingActivity extends AppCompatActivity {
    private ActivityLandingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setUpButtons();
    }

    private void setUpButtons(){
        binding.signInBtn.setOnClickListener(view -> goToSignIn());
        binding.signUpBtn.setOnClickListener(view -> goToSignUp());
    }

    private void goToSignIn(){
        Intent intent = new Intent(LandingActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    private void goToSignUp(){
        Intent intent = new Intent(LandingActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}