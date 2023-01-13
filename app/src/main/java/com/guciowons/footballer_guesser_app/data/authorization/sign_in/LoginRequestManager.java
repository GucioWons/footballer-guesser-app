package com.guciowons.footballer_guesser_app.data.authorization.sign_in;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.domain.authorization.sign_in.viewmodel.SignInViewModel;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class LoginRequestManager {
    public JsonObjectRequest getLoginRequest(SignInViewModel viewModel, JSONObject params){
        String url = "http://footballerguesserservice-env.eba-iwqz7xzh.eu-central-1.elasticbeanstalk.com/login/";
        return new JsonObjectRequest(Request.Method.POST, url, params,
                response -> viewModel.setResponse(response, params),
                error -> viewModel.setErrorResponse(getErrorResponse(error)));
    }

    private String getErrorResponse(VolleyError error){
        return new String(error.networkResponse.data, StandardCharsets.UTF_8);
    }
}
