package com.guciowons.footballer_guesser_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.helpers.StringHelper;
import com.guciowons.footballer_guesser_app.requests.RegisterRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText username_edittext, email_edittext, password_edittext, confirm_password_edittext;
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username_edittext = findViewById(R.id.username_edittext);
        email_edittext = findViewById(R.id.email_edittext);
        password_edittext = findViewById(R.id.password_edittext);
        confirm_password_edittext = findViewById(R.id.confirm_password_edittext);

        register_btn = findViewById(R.id.register_btn);
        register_btn.setOnClickListener(view -> processRegisterForm());
    }

    public void goBack(View view){
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void processRegisterForm(){
        if(!validateUserName() || !validateEmail() || !validatePassword()){
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
        RegisterRequest registerRequest = new RegisterRequest();
        requestQueue.add(registerRequest.getRegisterRequest(SignUpActivity.this,
                username_edittext, email_edittext, password_edittext, confirm_password_edittext));
    }

    public boolean validateUserName(){
        String username = username_edittext.getText().toString();
        if(username.isEmpty()){
            username_edittext.setError("Username cannot be empty!");
            return false;
        } else{
            username_edittext.setError(null);
            return true;
        }
    }

    public boolean validateEmail(){
        String email = email_edittext.getText().toString();
        if(email.isEmpty()){
            email_edittext.setError("Email cannot be empty!");
            return false;
        } else if(!StringHelper.dupa(email)){
            email_edittext.setError("Email is not valid!");
            return false;
        } else{
            email_edittext.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String password = password_edittext.getText().toString();
        String confirm_password = confirm_password_edittext.getText().toString();
        if(password.isEmpty()){
            password_edittext.setError("Password cannot be empty!");
            return false;
        } else if(confirm_password.isEmpty()){
            confirm_password_edittext.setError("Confirm password cannot be empty!");
            return false;
        } else if(!password.equals(confirm_password)){
            confirm_password_edittext.setError("Confirm password has to be the same as password!");
            return false;
        } else{
            password_edittext.setError(null);
            confirm_password_edittext.setError(null);
            return true;
        }
    }
}