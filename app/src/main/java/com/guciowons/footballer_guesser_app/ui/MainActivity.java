package com.guciowons.footballer_guesser_app.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.requests.AuthenticationRequestsManager;
import com.guciowons.footballer_guesser_app.data.preferences.EncryptedPreferencesGetter;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Integer id;
    private String email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        loadEncryptedPreferences();
        authenticateUser();

        //TODO
        //Cant get encrypted preferences after reinstall
    }

    private void loadEncryptedPreferences(){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        loadData(encryptedPreferencesGetter.getEncryptedPreferences(this));
    }

    private void loadData(SharedPreferences account){
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

    public void closeSplashScreen() {
        new Handler().postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, LandingActivity.class));
            finish();
        }, 2000);
    }
}