package com.guciowons.footballer_guesser_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.helpers.StringHelper;
import com.guciowons.footballer_guesser_app.requests.LoginRequest;
import com.guciowons.footballer_guesser_app.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.validators.PasswordValidator;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
    private EditText email_edittext, password_edittext;
    private Button login_btn;

    private EmailValidator emailValidator = new EmailValidator();
    private PasswordValidator passwordValidator = new PasswordValidator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email_edittext = findViewById(R.id.email_edittext);
        password_edittext = findViewById(R.id.password_edittext);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(view -> authenticateUser());
    }

    public void authenticateUser(){
        if(emailValidator.validateEmail(email_edittext) &&
                passwordValidator.validatePassword(password_edittext)){
            RequestQueue requestQueue = Volley.newRequestQueue(SignInActivity.this);
            LoginRequest loginRequest = new LoginRequest();
            SharedPreferences sharedPreferences = getSharedPreferences("Account", MODE_PRIVATE);
            requestQueue.add(loginRequest.getLoginRequest(SignInActivity.this, getParamsJson(), sharedPreferences));
        }
    }

    public JSONObject getParamsJson(){
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email_edittext.getText().toString());
        params.put("password", password_edittext.getText().toString());
        return new JSONObject(params);
    }

    public void goBack(View view){
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}