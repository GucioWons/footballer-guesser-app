package com.guciowons.footballer_guesser_app.requests;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.guciowons.footballer_guesser_app.SignUpActivity;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest {
    public JsonObjectRequest getRegisterRequest(SignUpActivity signUpActivity,
                                                EditText username_edittext,
                                                EditText email_edittext,
                                                EditText password_edittext,
                                                EditText confirm_password_edittext){
        Map<String, String> params = new HashMap<>();
        params.put("username", username_edittext.getText().toString());
        params.put("email", email_edittext.getText().toString());
        params.put("password", password_edittext.getText().toString());
        return new JsonObjectRequest(Request.Method.POST, "http://192.168.0.2:8080/register", new JSONObject(params), response -> {
            Toast.makeText(signUpActivity, "Success", Toast.LENGTH_SHORT).show();
            clearForm(username_edittext, email_edittext, password_edittext, confirm_password_edittext);
        }, error -> {
            String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
            Toast.makeText(signUpActivity, body, Toast.LENGTH_SHORT).show();
        });
    }

    private void clearForm(EditText username_edittext,
                           EditText email_edittext,
                           EditText password_edittext,
                           EditText confirm_password_edittext){
        username_edittext.setText(null);
        email_edittext.setText(null);
        password_edittext.setText(null);
        confirm_password_edittext.setText(null);
    }
}
