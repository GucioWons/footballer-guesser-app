package com.guciowons.footballer_guesser_app.data.requests;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.domain.models.SignInViewModel;
import com.guciowons.footballer_guesser_app.domain.models.SignUpViewModel;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class LoginRequestManager {
    public JsonObjectRequest getLoginRequest(SignInViewModel model, JSONObject params){
        String url = "http://192.168.0.2:8080/login/";
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
