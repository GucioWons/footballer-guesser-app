package com.guciowons.footballer_guesser_app.ui.authentication.signin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.guciowons.footballer_guesser_app.databinding.ActivitySignInBinding;
import com.guciowons.footballer_guesser_app.domain.authentication.signin.view_model.SignInViewModel;
import com.guciowons.footballer_guesser_app.ui.authentication.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.ui.authentication.validators.PasswordValidator;
import com.guciowons.footballer_guesser_app.ui.authentication.splash.activity.SplashActivity;
import com.guciowons.footballer_guesser_app.ui.leagues.activity.LeaguesActivity;

public class SignInActivity extends AppCompatActivity {
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
        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
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
        boolean emailCorrect = validateEmail();
        boolean passwordCorrect = validatePassword();
        return emailCorrect && passwordCorrect;
    }

    private boolean validateEmail(){
        String emailResponse = EmailValidator.validateEmail(binding.emailEt.getEditText().getText().toString());
        if(!emailResponse.equals("Success")){
            binding.emailEt.setError(emailResponse);
            return false;
        }
        return true;
    }

    private boolean validatePassword(){
        String passwordResponse = PasswordValidator.validatePassword(binding.passwordEt.getEditText().getText().toString());
        if(!passwordResponse.equals("Success")){
            binding.passwordEt.setError(passwordResponse);
            return false;
        }
        return true;
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