package com.guciowons.footballer_guesser_app.ui.authentication.landing.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.databinding.ActivityLandingBinding;
import com.guciowons.footballer_guesser_app.ui.authentication.signin.activity.SignInActivity;
import com.guciowons.footballer_guesser_app.ui.authentication.signup.activity.SignUpActivity;

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

    public void goToSignIn(){
        Intent intent = new Intent(LandingActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void goToSignUp(){
        Intent intent = new Intent(LandingActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}