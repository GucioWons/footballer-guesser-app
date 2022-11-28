package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import android.widget.EditText;

public class PasswordValidator {
    public boolean validatePassword(EditText editText){
        String password = editText.getText().toString();
        if(password.isEmpty()){
            editText.setError("Password cannot be empty!");
            return false;
        } else{
            editText.setError(null);
            return true;
        }
    }
}
