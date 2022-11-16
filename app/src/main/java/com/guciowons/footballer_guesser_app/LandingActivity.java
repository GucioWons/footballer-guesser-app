package com.guciowons.footballer_guesser_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.authentication.activities.SignInActivity;
import com.guciowons.footballer_guesser_app.authentication.activities.SignUpActivity;
import com.guciowons.footballer_guesser_app.authentication.requests.AuthenticationRequestsManager;
import com.guciowons.footballer_guesser_app.preferences.EncryptedPreferencesGetter;

import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;

public class LandingActivity extends AppCompatActivity {
    private Button signInBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        signInBtn = findViewById(R.id.sign_in_btn);
        signUpBtn = findViewById(R.id.sign_up_btn);

        signInBtn.setOnClickListener(view -> goToSignIn());
        signUpBtn.setOnClickListener(view -> goToSignUp());
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