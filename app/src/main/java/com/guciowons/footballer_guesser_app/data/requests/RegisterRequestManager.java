package com.guciowons.footballer_guesser_app.data.requests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.data.preferences.EncryptedPreferencesGetter;
import com.guciowons.footballer_guesser_app.domain.models.SignUpViewModel;
import com.guciowons.footballer_guesser_app.ui.game.activities.LeaguesActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class RegisterRequestManager {
    public JsonObjectRequest getRegisterRequest(SignUpViewModel model, JSONObject params){
        String url = "http://192.168.0.2:8080/register/";
        return new JsonObjectRequest(Request.Method.POST, url, params,
                response -> {
                    model.setResponse(response, params);
                }, error -> {
                    model.setErrorResponse(getErrorResponse(error));
        });
    }

    private String getErrorResponse(VolleyError error){
        return new String(error.networkResponse.data, StandardCharsets.UTF_8);
    }
}
