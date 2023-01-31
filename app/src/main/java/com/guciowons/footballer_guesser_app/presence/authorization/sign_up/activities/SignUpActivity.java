package com.guciowons.footballer_guesser_app.presence.authorization.sign_up.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.guciowons.footballer_guesser_app.presence.authorization.BaseAuthActivity;
import com.guciowons.footballer_guesser_app.presence.authorization.splash.activities.SplashActivity;
import com.guciowons.footballer_guesser_app.databinding.ActivitySignUpBinding;
import com.guciowons.footballer_guesser_app.domain.authorization.sign_up.viewmodel.SignUpViewModel;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.ConfirmPasswordValidator;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.DefaultTextValidator;

import java.util.Objects;

public class SignUpActivity extends BaseAuthActivity {
    private SignUpViewModel signUpViewModel;
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpViewModel();
        setUpButtons();
    }

    private void setUpViewModel(){
        signUpViewModel = new SignUpViewModel(getApplication());
        setUpResponseObserver();
    }

    private void setUpResponseObserver(){
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
            signUpViewModel.signUpUser(Objects.requireNonNull(binding.usernameEt.getEditText()).getText().toString(),
                    Objects.requireNonNull(binding.emailEt.getEditText()).getText().toString(),
                    Objects.requireNonNull(binding.passwordEt.getEditText()).getText().toString());
        }
    }

    private boolean validateEditTexts(){
        boolean usernameCorrect = validateUsername(binding.usernameEt);
        boolean emailCorrect = validateEmail(binding.emailEt);
        boolean passwordCorrect = validatePassword(binding.passwordEt);
        boolean confirmCorrect = validateConfirm(binding.confirmEt, binding.passwordEt);
        return usernameCorrect && emailCorrect && passwordCorrect && confirmCorrect;
    }

    private boolean validateUsername(TextInputLayout usernameEt){
        String usernameResponse = DefaultTextValidator.validateText(Objects.requireNonNull(usernameEt.getEditText()).getText().toString(), "Username");
        if(!usernameResponse.equals("Success")){
            usernameEt.setError(usernameResponse);
            return false;
        }
        usernameEt.setError(null);
        return true;
    }

    private boolean validateConfirm(TextInputLayout confirmEt, TextInputLayout passwordEt){
        String confirmResponse = ConfirmPasswordValidator.validateConfirmPassword(
                Objects.requireNonNull(passwordEt.getEditText()).getText().toString(), Objects.requireNonNull(confirmEt.getEditText()).getText().toString());
        if(!confirmResponse.equals("Success")){
            confirmEt.setError(confirmResponse);
            return false;
        }
        confirmEt.setError(null);
        return true;
    }

    private void goBack(){
        Intent intent = new Intent(SignUpActivity.this, SplashActivity.class);
        startActivity(intent);
    }
}