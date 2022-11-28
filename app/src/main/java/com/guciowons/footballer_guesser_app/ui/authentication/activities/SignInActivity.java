package com.guciowons.footballer_guesser_app.ui.authentication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.guciowons.footballer_guesser_app.databinding.ActivitySignInBinding;
import com.guciowons.footballer_guesser_app.domain.models.SignInViewModel;
import com.guciowons.footballer_guesser_app.ui.SplashActivity;
import com.guciowons.footballer_guesser_app.domain.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.domain.validators.PasswordValidator;
import com.guciowons.footballer_guesser_app.ui.game.activities.LeaguesActivity;

public class SignInActivity extends AppCompatActivity {
    private SignInViewModel signInViewModel;

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpButtons();

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        signInViewModel.getResponse().observe(this, response -> {
            if(response.equals("Success")){
                authenticateUser();
            }else{
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setUpButtons(){
        binding.loginBtn.setOnClickListener(view -> processLoginForm());
        binding.backBtn.setOnClickListener(view -> goBack());
    }

    public void processLoginForm(){
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        if(emailValidator.validateEmail(binding.emailEt) && passwordValidator.validatePassword(binding.passwordEt)){
            signInViewModel.logInUser(binding.emailEt.getText().toString(), binding.passwordEt.getText().toString());
        }
    }

    private void authenticateUser(){
        Intent intent = new Intent(this, LeaguesActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    public void goBack(){
        Intent intent = new Intent(SignInActivity.this, SplashActivity.class);
        startActivity(intent);
    }
}