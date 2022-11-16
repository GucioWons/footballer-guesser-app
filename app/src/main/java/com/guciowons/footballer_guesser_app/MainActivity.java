package com.guciowons.footballer_guesser_app;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.authentication.requests.AuthenticationRequestsManager;
import com.guciowons.footballer_guesser_app.preferences.EncryptedPreferencesGetter;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Integer id;
    private String email, username, password;

    private SharedPreferences account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        account = encryptedPreferencesGetter.getEncryptedPreferences(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        loadData();
        authenticateUser();

        //TODO
        //Cant get encrypted preferences after reinstall
    }

    private void loadData(){
        id = account.getInt("id", 0);
        email = account.getString("email", null);
        username = account.getString("username", null);
        password = account.getString("password", null);
    }

    private void authenticateUser(){
        if(id != 0 || email != null || username != null || password != null) {
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            AuthenticationRequestsManager authenticationRequestsManager = new AuthenticationRequestsManager();
            requestQueue.add(authenticationRequestsManager.getAuthenticationRequest(MainActivity.this, getParamsJson(), "login"));
        }else{
            closeSplashScreen();
        }
    }

    private JSONObject getParamsJson(){
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        return new JSONObject(params);
    }

    private void closeSplashScreen() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, LandingActivity.class));
            finish();
        }, 2000);
    }
}