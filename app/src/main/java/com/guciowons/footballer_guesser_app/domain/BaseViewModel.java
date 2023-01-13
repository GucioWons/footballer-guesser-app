package com.guciowons.footballer_guesser_app.domain;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

public abstract class BaseViewModel {
    protected Application application;
    protected SharedPreferences account;
    protected MutableLiveData<String> error = new MutableLiveData<>();



    public BaseViewModel(Application application) {
        this.application = application;
        account = getEncryptedPreferences();
    }

    private SharedPreferences getEncryptedPreferences(){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        return encryptedPreferencesGetter.getEncryptedPreferences(application);
    }

    public void logoutUser(){
        account.edit().clear().apply();
    }

    public MutableLiveData<String> getError(){
        return error;
    }
}
