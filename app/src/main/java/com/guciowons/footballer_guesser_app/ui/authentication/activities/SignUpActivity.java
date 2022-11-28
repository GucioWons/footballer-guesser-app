package com.guciowons.footballer_guesser_app.ui.authentication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.guciowons.footballer_guesser_app.databinding.ActivitySignUpBinding;
import com.guciowons.footballer_guesser_app.domain.models.SignUpViewModel;
import com.guciowons.footballer_guesser_app.ui.SplashActivity;
import com.guciowons.footballer_guesser_app.domain.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.domain.validators.PasswordAndConfirmValidator;
import com.guciowons.footballer_guesser_app.domain.validators.UsernameValidator;
import com.guciowons.footballer_guesser_app.ui.game.activities.LeaguesActivity;

public class SignUpActivity extends AppCompatActivity {
    private SignUpViewModel signUpViewModel;
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpButtons();

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

    public void goBack(){
        Intent intent = new Intent(SignUpActivity.this, SplashActivity.class);
        startActivity(intent);
    }

    private void authenticateUser(){
        Intent intent = new Intent(this, LeaguesActivity.class);
        startActivity(intent);
        finishAffinity();
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
}