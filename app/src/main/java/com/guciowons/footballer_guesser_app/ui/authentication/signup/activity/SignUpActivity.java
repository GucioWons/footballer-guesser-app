package com.guciowons.footballer_guesser_app.ui.authentication.signup.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.guciowons.footballer_guesser_app.databinding.ActivitySignUpBinding;
import com.guciowons.footballer_guesser_app.domain.authentication.signup.view_model.SignUpViewModel;
import com.guciowons.footballer_guesser_app.ui.authentication.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.ui.authentication.validators.PasswordAndConfirmValidator;
import com.guciowons.footballer_guesser_app.ui.authentication.splash.activity.SplashActivity;
import com.guciowons.footballer_guesser_app.ui.authentication.validators.UsernameValidator;
import com.guciowons.footballer_guesser_app.ui.leagues.activity.LeaguesActivity;

public class SignUpActivity extends AppCompatActivity {
    private SignUpViewModel signUpViewModel;
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpObserver();
        setUpButtons();
    }

    private void setUpObserver(){
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        signUpViewModel.getResponse().observe(this, response -> {
            if(response.equals("Success")){
                authenticateUser();
            }else{
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpButtons(){
        binding.registerBtn.setOnClickListener(view -> processRegisterForm());
        binding.backBtn.setOnClickListener(view -> goBack());
    }

    private void processRegisterForm(){
        UsernameValidator usernameValidator = new UsernameValidator();
        EmailValidator emailValidator = new EmailValidator();
        PasswordAndConfirmValidator passwordAndConfirmValidator = new PasswordAndConfirmValidator();
        if(usernameValidator.validateUsername(binding.usernameEt) &&
                emailValidator.validateEmail(binding.emailEt) &&
                passwordAndConfirmValidator.validatePasswordAndConfirm(binding.passwordEt,
                        binding.confirmEt)){
            signUpViewModel.signUpUser(binding.usernameEt.getText().toString(),
                    binding.emailEt.getText().toString(),
                    binding.passwordEt.getText().toString());
        }
    }

    private void authenticateUser(){
        Intent intent = new Intent(this, LeaguesActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    public void goBack(){
        Intent intent = new Intent(SignUpActivity.this, SplashActivity.class);
        startActivity(intent);
    }
}