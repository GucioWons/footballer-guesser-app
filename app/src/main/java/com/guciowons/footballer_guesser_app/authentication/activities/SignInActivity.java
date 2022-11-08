package com.guciowons.footballer_guesser_app.authentication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.MainActivity;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.authentication.requests.LoginRequest;
import com.guciowons.footballer_guesser_app.authentication.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.authentication.validators.PasswordValidator;

import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
    private EditText emailEt, passwordEt;
    private Button loginBtn;

    private EmailValidator emailValidator = new EmailValidator();
    private PasswordValidator passwordValidator = new PasswordValidator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(view -> authenticateUser());
    }

    public void authenticateUser(){
        if(emailValidator.validateEmail(emailEt) &&
                passwordValidator.validatePassword(passwordEt)){
            RequestQueue requestQueue = Volley.newRequestQueue(SignInActivity.this);
            LoginRequest loginRequest = new LoginRequest();
            requestQueue.add(loginRequest.getLoginRequest(SignInActivity.this, getParamsJson()));
        }
    }

    public JSONObject getParamsJson(){
        HashMap<String, String> params = new HashMap<>();
        params.put("email", emailEt.getText().toString());
        params.put("password", passwordEt.getText().toString());
        return new JSONObject(params);
    }

    public void goBack(View view){
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}