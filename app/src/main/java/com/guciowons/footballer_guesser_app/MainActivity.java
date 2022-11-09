package com.guciowons.footballer_guesser_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.authentication.activities.SignInActivity;
import com.guciowons.footballer_guesser_app.authentication.activities.SignUpActivity;
import com.guciowons.footballer_guesser_app.authentication.requests.LoginRequest;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button sign_in_btn, sign_up_btn;

    private Integer id;
    private String email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_in_btn = findViewById(R.id.sign_in_btn);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        authenticateUser();

        sign_in_btn.setOnClickListener(view -> goToSignIn());
        sign_up_btn.setOnClickListener(view -> goToSignUp());
    }

    public void authenticateUser(){
        loadData();
        if(id != 0 || email != null || username != null || password != null){
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            LoginRequest loginRequest = new LoginRequest();
            requestQueue.add(loginRequest.getLoginRequest(MainActivity.this, getParamsJson()));
        }
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Account", MODE_PRIVATE);
        id = sharedPreferences.getInt("id", 0);
        email = sharedPreferences.getString("email", null);
        username = sharedPreferences.getString("username", null);
        password = sharedPreferences.getString("password", null);
    }

    public JSONObject getParamsJson(){
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        return new JSONObject(params);
    }

    public void goToSignIn(){
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void goToSignUp(){
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}