package com.guciowons.footballer_guesser_app.requests;

import android.content.Context;
import android.content.Intent;
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
import com.guciowons.footballer_guesser_app.LeaguesActivity;
import com.guciowons.footballer_guesser_app.SignInActivity;
import com.guciowons.footballer_guesser_app.SignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest {
    public JsonObjectRequest getRegisterRequest(SignUpActivity signUpActivity, JSONObject params, SharedPreferences sharedPreferences){
        return new JsonObjectRequest(Request.Method.POST, "http://192.168.0.2:8080/register", params,
                response -> {
                    saveData(response, sharedPreferences);
                    userRegistered(signUpActivity);
                }, error -> {
                    String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    Toast.makeText(signUpActivity, body, Toast.LENGTH_SHORT).show();
                });
    }

    private void userRegistered(SignUpActivity signUpActivity){
        Toast.makeText(signUpActivity, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(signUpActivity, LeaguesActivity.class);
        signUpActivity.startActivity(intent);
        signUpActivity.finish();
    }

    private void saveData(JSONObject response, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putInt("id", response.getInt("id"));
            editor.putString("username", response.getString("username"));
            editor.putString("email", response.getString("email"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
