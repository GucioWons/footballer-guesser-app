package com.guciowons.footballer_guesser_app.authentication.requests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.game.LeaguesActivity;
import com.guciowons.footballer_guesser_app.authentication.activities.SignUpActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class RegisterRequest {
    public JsonObjectRequest getRegisterRequest(SignUpActivity signUpActivity, JSONObject params){
        return new JsonObjectRequest(Request.Method.POST, "http://192.168.0.3:8080/register", params,
                response -> {
                    saveData(response, params, signUpActivity.getSharedPreferences("Account", 0));
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

    private void saveData(JSONObject response, JSONObject params, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putInt("id", response.getInt("id"));
            editor.putString("username", response.getString("username"));
            editor.putString("email", response.getString("email"));
            editor.putString("password", params.getString("password"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
