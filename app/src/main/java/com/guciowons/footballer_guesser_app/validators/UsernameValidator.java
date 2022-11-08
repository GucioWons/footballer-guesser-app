package com.guciowons.footballer_guesser_app.validators;

import android.widget.EditText;

public class UsernameValidator {
    public boolean validateUsername(EditText editText){
        String username = editText.getText().toString();
        if(username.isEmpty()){
            editText.setError("Username cannot be empty!");
            return false;
        } else{
            editText.setError(null);
            return true;
        }
    }
}
