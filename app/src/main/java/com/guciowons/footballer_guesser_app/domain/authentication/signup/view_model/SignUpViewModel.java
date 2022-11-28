package com.guciowons.footballer_guesser_app.domain.authentication.signup.view_model;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.domain.authentication.signup.request.RegisterRequestManager;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignUpViewModel extends AndroidViewModel {
    private RequestQueue requestQueue;
    private MutableLiveData<String> response = new MutableLiveData<>();

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
    }

    public MutableLiveData<String> getResponse(){
        return response;
    }

    public void signUpUser(String username, String email, String password){
        RegisterRequestManager registerRequestManager = new RegisterRequestManager();
        requestQueue.add(registerRequestManager.getRegisterRequest(this, getJsonParams(username, email, password)));
    }

    private JSONObject getJsonParams(String username, String email, String password){
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);
        return new JSONObject(params);
    }

    public void setResponse(JSONObject user, JSONObject params){
        saveData(user, params);
        response.setValue("Success");
    }

    private void saveData(JSONObject user, JSONObject params){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        SharedPreferences sharedPreferences = encryptedPreferencesGetter.getEncryptedPreferences(getApplication());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putInt("id", user.getInt("id"));
            editor.putString("username", user.getString("username"));
            editor.putString("email", user.getString("email"));
            editor.putString("password", params.getString("password"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setErrorResponse(String error){
        response.setValue(error);
    }
}
