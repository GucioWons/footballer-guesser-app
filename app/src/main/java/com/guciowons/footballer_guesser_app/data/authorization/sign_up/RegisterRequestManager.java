package com.guciowons.footballer_guesser_app.data.authorization.sign_up;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.domain.authorization.sign_up.viewmodel.SignUpViewModel;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class RegisterRequestManager {
    public JsonObjectRequest getRegisterRequest(SignUpViewModel model, JSONObject params){
        String url = "http://192.168.0.8:8080/register/";
        return new JsonObjectRequest(Request.Method.POST, url, params,
                response -> model.setResponse(response, params),
                error -> model.setErrorResponse(getErrorResponse(error)));
    }

    private String getErrorResponse(VolleyError error){
        return new String(error.networkResponse.data, StandardCharsets.UTF_8);
    }
}
