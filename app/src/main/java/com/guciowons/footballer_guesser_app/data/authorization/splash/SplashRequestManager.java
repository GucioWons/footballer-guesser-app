package com.guciowons.footballer_guesser_app.data.authorization.splash;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.domain.authorization.splash.viewmodel.SplashViewModel;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class SplashRequestManager {
    public JsonObjectRequest getLoginRequest(SplashViewModel model, JSONObject params){
        String url = "http://192.168.0.8:8080/login/";
        return new JsonObjectRequest(Request.Method.POST, url, params,
                response -> model.setResponse(response, params),
                error -> model.setErrorResponse(getErrorResponse(error)));
    }

    private String getErrorResponse(VolleyError error){
        return new String(error.networkResponse.data, StandardCharsets.UTF_8);
    }
}
