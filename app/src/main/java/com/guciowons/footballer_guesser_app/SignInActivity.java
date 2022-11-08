package com.guciowons.footballer_guesser_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
    EditText email_edittext, password_edittext;
    Button login_btn;

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
        if(!validateEmail() || !validatePassword()){
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(SignInActivity.this);
        String url = "http://192.168.0.2:8080/login";
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email_edittext.getText().toString());
        params.put("password", password_edittext.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                response -> {
                    try {
                        Integer id = response.getInt("id");
                        String username = response.getString("username");
                        String email = response.getString("email");
                        Toast.makeText(SignInActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
                    String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    Toast.makeText(SignInActivity.this, body, Toast.LENGTH_SHORT).show();
                });
        requestQueue.add(jsonObjectRequest);
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
        if(password.isEmpty()){
            password_edittext.setError("Password cannot be empty!");
            return false;
        } else{
            password_edittext.setError(null);
            return true;
        }
    }

    public void goBack(View view){
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}