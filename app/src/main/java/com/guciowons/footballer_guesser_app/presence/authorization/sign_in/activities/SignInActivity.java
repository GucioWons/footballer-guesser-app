package com.guciowons.footballer_guesser_app.presence.authorization.sign_in.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.guciowons.footballer_guesser_app.presence.authorization.BaseAuthActivity;
import com.guciowons.footballer_guesser_app.presence.authorization.splash.activities.SplashActivity;
import com.guciowons.footballer_guesser_app.presence.leagues.activities.LeaguesActivity;
import com.guciowons.footballer_guesser_app.databinding.ActivitySignInBinding;
import com.guciowons.footballer_guesser_app.domain.authorization.sign_in.viewmodel.SignInViewModel;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.PasswordValidator;

public class SignInActivity extends BaseAuthActivity {
    private SignInViewModel signInViewModel;

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setUpObserver();
        setUpButtons();
    }

    private void setUpObserver(){
        signInViewModel = new SignInViewModel(getApplication());
        signInViewModel.getResponse().observe(this, response -> {
            if(response.equals("Success")){
                authenticateUser();
            }else{
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpButtons(){
        binding.loginBtn.setOnClickListener(view -> processLoginForm());
        binding.backBtn.setOnClickListener(view -> goBack());
    }

    public void processLoginForm(){
        if(validateEditTexts()){
            signInViewModel.logInUser(binding.emailEt.getEditText().getText().toString(), binding.passwordEt.getEditText().getText().toString());
        }
    }

    private boolean validateEditTexts(){
        boolean emailCorrect = validateEmail(binding.emailEt);
        boolean passwordCorrect = validatePassword(binding.passwordEt);
        return emailCorrect && passwordCorrect;
    }

    public void goBack(){
        Intent intent = new Intent(SignInActivity.this, SplashActivity.class);
        startActivity(intent);
    }
}