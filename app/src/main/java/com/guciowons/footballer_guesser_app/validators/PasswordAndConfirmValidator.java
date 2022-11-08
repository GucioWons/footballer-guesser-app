package com.guciowons.footballer_guesser_app.validators;

import android.widget.EditText;

public class PasswordAndConfirmValidator {
    public boolean validatePasswordAndConfirm(EditText passwordEditText, EditText confirmEditText){
        String password = passwordEditText.getText().toString();
        String confirm_password = confirmEditText.getText().toString();
        if(password.isEmpty()){
            passwordEditText.setError("Password cannot be empty!");
            return false;
        } else if(confirm_password.isEmpty()){
            confirmEditText.setError("Confirm password cannot be empty!");
            return false;
        } else if(!password.equals(confirm_password)){
            confirmEditText.setError("Confirm password has to be the same as password!");
            return false;
        } else{
            passwordEditText.setError(null);
            confirmEditText.setError(null);
            return true;
        }
    }
}
