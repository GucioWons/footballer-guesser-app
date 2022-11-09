package com.guciowons.footballer_guesser_app.authentication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.MainActivity;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.authentication.requests.AuthenticationRequestsManager;
import com.guciowons.footballer_guesser_app.authentication.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.authentication.validators.PasswordAndConfirmValidator;
import com.guciowons.footballer_guesser_app.authentication.validators.UsernameValidator;

import org.json.JSONObject;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private EditText usernameEt, emailEt, passwordEt, confirmEt;
    private Button registerBtn, goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEt = findViewById(R.id.username_et);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        confirmEt = findViewById(R.id.confirm_et);

        registerBtn = findViewById(R.id.register_btn);
        goBackBtn = findViewById(R.id.back_btn);
        registerBtn.setOnClickListener(view -> processRegisterForm());
        goBackBtn.setOnClickListener(view -> goBack());
    }

    public void goBack(){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void processRegisterForm(){
        UsernameValidator usernameValidator = new UsernameValidator();
        EmailValidator emailValidator = new EmailValidator();
        PasswordAndConfirmValidator passwordAndConfirmValidator = new PasswordAndConfirmValidator();
        if(usernameValidator.validateUsername(usernameEt) &&
                emailValidator.validateEmail(emailEt) &&
                passwordAndConfirmValidator.validatePasswordAndConfirm(passwordEt, confirmEt)){
            RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
            AuthenticationRequestsManager authenticationRequestsManager = new AuthenticationRequestsManager();
            requestQueue.add(authenticationRequestsManager.getAuthenticationRequest(SignUpActivity.this, getParamsJson(), "register"));
        }
    }

    public JSONObject getParamsJson(){
        HashMap<String, String> params = new HashMap<>();
        params.put("username", usernameEt.getText().toString());
        params.put("email", emailEt.getText().toString());
        params.put("password", passwordEt.getText().toString());
        return new JSONObject(params);
    }
}