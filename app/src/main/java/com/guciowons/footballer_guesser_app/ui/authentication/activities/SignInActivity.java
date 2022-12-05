package com.guciowons.footballer_guesser_app.ui.authentication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.ui.MainActivity;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.requests.AuthenticationRequestsManager;
import com.guciowons.footballer_guesser_app.domain.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.domain.validators.PasswordValidator;

import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
    private EditText emailEt, passwordEt;
    private Button loginBtn, goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        goBackBtn = findViewById(R.id.back_btn);

        loginBtn.setOnClickListener(view -> processLoginForm());
        goBackBtn.setOnClickListener(view -> goBack());
    }

    public void processLoginForm(){
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        if(emailValidator.validateEmail(emailEt) && passwordValidator.validatePassword(passwordEt)){
            authenticateUser();
        }
    }

    private void authenticateUser(){
        RequestQueue requestQueue = Volley.newRequestQueue(SignInActivity.this);
        AuthenticationRequestsManager authenticationRequestsManager = new AuthenticationRequestsManager();
        requestQueue.add(authenticationRequestsManager.getAuthenticationRequest(SignInActivity.this, getParamsJson(), "login"));
    }

    private JSONObject getParamsJson(){
        HashMap<String, String> params = new HashMap<>();
        params.put("email", emailEt.getText().toString());
        params.put("password", passwordEt.getText().toString());
        return new JSONObject(params);
    }

    public void goBack(){
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
    }
}