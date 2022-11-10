package com.guciowons.footballer_guesser_app.authentication.validators;

import android.widget.EditText;

public class PasswordAndConfirmValidator {
    public boolean validatePasswordAndConfirm(EditText passwordEt, EditText confirmEt){
        String password = passwordEt.getText().toString();
        String confirm_password = confirmEt.getText().toString();
        if(password.isEmpty()){
            passwordEt.setError("Password cannot be empty!");
            return false;
        } else if(confirm_password.isEmpty()){
            confirmEt.setError("Confirm password cannot be empty!");
            return false;
        } else if(!password.equals(confirm_password)){
            confirmEt.setError("Confirm password has to be the same as password!");
            return false;
        } else{
            passwordEt.setError(null);
            confirmEt.setError(null);
            return true;
        }
    }
}
