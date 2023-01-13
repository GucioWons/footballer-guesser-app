package com.guciowons.footballer_guesser_app.domain.authorization;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseAuthViewModel {
    protected RequestQueue requestQueue;
    private MutableLiveData<String> response = new MutableLiveData<>();
    protected Application application;

    public BaseAuthViewModel(Application application) {
        this.application = application;
        requestQueue = Volley.newRequestQueue(application);
    }

    private void saveData(JSONObject user, JSONObject params){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        SharedPreferences sharedPreferences = encryptedPreferencesGetter.getEncryptedPreferences(application);
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

    public void setResponse(JSONObject user, JSONObject params){
        saveData(user, params);
        response.setValue("Success");
    }

    public MutableLiveData<String> getResponse(){
        return response;
    }
}
