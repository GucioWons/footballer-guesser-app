package com.guciowons.footballer_guesser_app.data;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public abstract class BaseRepository {
    private final MutableLiveData<String> error = new MutableLiveData<>();
    protected final RequestQueue requestQueue;

    public BaseRepository(Application application) {
        requestQueue = Volley.newRequestQueue(application);
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public void setError(String error) {
        if(this.error.getValue() == null || !this.error.getValue().equals(error)){
            this.error.setValue(error);
        }
    }

    public MutableLiveData<String> getError(){
        return error;
    }
}
