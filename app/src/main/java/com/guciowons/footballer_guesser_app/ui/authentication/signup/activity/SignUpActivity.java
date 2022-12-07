package com.guciowons.footballer_guesser_app.ui.authentication.signup.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.guciowons.footballer_guesser_app.databinding.ActivitySignUpBinding;
import com.guciowons.footballer_guesser_app.domain.authentication.signup.view_model.SignUpViewModel;
import com.guciowons.footballer_guesser_app.ui.authentication.validators.ConfirmPasswordValidator;
import com.guciowons.footballer_guesser_app.ui.authentication.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.ui.authentication.splash.activity.SplashActivity;
import com.guciowons.footballer_guesser_app.ui.authentication.validators.PasswordValidator;
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
        if(validateEditTexts()){
            signUpViewModel.signUpUser(binding.usernameEt.getText().toString(),
                    binding.emailEt.getText().toString(),
                    binding.passwordEt.getText().toString());
        }
    }

    private boolean validateEditTexts(){
        boolean usernameCorrect = validateUsername();
        boolean emailCorrect = validateEmail();
        boolean passwordCorrect = validatePassword();
        boolean confirmCorrect = validateConfirm();
        return usernameCorrect && emailCorrect && passwordCorrect && confirmCorrect;
    }

    private boolean validateUsername(){
        String usernameResponse = UsernameValidator.validateUsername(binding.usernameEt.getText().toString());
        if(!usernameResponse.equals("Success")){
            binding.usernameEt.setError(usernameResponse);
            return false;
        }
        return true;
    }

    private boolean validateEmail(){
        String emailResponse = EmailValidator.validateEmail(binding.emailEt.getText().toString());
        if(!emailResponse.equals("Success")){
            binding.emailEt.setError(emailResponse);
            return false;
        }
        return true;
    }

    private boolean validatePassword(){
        String passwordResponse = PasswordValidator.validatePassword(binding.passwordEt.getText().toString());
        if(!passwordResponse.equals("Success")){
            binding.passwordEt.setError(passwordResponse);
            return false;
        }
        return true;
    }

    private boolean validateConfirm(){
        String confirmResponse = ConfirmPasswordValidator.validateConfirmPassword(
                binding.passwordEt.getText().toString(), binding.confirmEt.getText().toString());
        if(!confirmResponse.equals("Success")){
            binding.confirmEt.setError(confirmResponse);
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
        Intent intent = new Intent(SignUpActivity.this, SplashActivity.class);
        startActivity(intent);
    }
}