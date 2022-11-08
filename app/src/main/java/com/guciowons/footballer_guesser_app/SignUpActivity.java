package com.guciowons.footballer_guesser_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.guciowons.footballer_guesser_app.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.validators.PasswordAndConfirmValidator;
import com.guciowons.footballer_guesser_app.validators.UsernameValidator;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private EditText username_edittext, email_edittext, password_edittext, confirm_password_edittext;
    private Button register_btn;

    private UsernameValidator usernameValidator = new UsernameValidator();
    private EmailValidator emailValidator = new EmailValidator();
    private PasswordAndConfirmValidator passwordAndConfirmValidator = new PasswordAndConfirmValidator();

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
        if(usernameValidator.validateUsername(username_edittext) &&
                emailValidator.validateEmail(email_edittext) &&
                passwordAndConfirmValidator.validatePasswordAndConfirm(password_edittext, confirm_password_edittext)){
            RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
            RegisterRequest registerRequest = new RegisterRequest();
            SharedPreferences sharedPreferences = getSharedPreferences("Account", MODE_PRIVATE);
            requestQueue.add(registerRequest.getRegisterRequest(SignUpActivity.this, getParamsJson(), sharedPreferences));
        }
    }

    public JSONObject getParamsJson(){
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username_edittext.getText().toString());
        params.put("email", email_edittext.getText().toString());
        params.put("password", password_edittext.getText().toString());
        return new JSONObject(params);
    }
}