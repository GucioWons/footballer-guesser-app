package com.guciowons.footballer_guesser_app.presence.authorization;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.PasswordValidator;
import com.guciowons.footballer_guesser_app.presence.leagues.activities.LeaguesActivity;

import java.util.Objects;

public abstract class BaseAuthActivity extends AppCompatActivity {
    protected boolean validateEmail(TextInputLayout emailEt){
        String emailResponse = EmailValidator.validateEmail(Objects.requireNonNull(emailEt.getEditText()).getText().toString());
        if(!emailResponse.equals("Success")){
            emailEt.setError(emailResponse);
            return false;
        }
        emailEt.setError(null);
        return true;
    }

    protected boolean validatePassword(TextInputLayout passwordEt){
        String passwordResponse = PasswordValidator.validatePassword(Objects.requireNonNull(passwordEt.getEditText()).getText().toString());
        if(!passwordResponse.equals("Success")){
            passwordEt.setError(passwordResponse);
            return false;
        }
        passwordEt.setError(null);
        return true;
    }

    protected void authenticateUser(){
        Intent intent = new Intent(this, LeaguesActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}
