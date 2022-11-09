package com.guciowons.footballer_guesser_app.authentication.validators;

import android.widget.EditText;

import com.guciowons.footballer_guesser_app.authentication.helpers.StringHelper;

public class EmailValidator {
    public boolean validateEmail(EditText editText) {
        String email = editText.getText().toString();
        StringHelper stringHelper = new StringHelper();
        if (email.isEmpty()) {
            editText.setError("Email cannot be empty!");
            return false;
        } else if (!stringHelper.validateEmail(email)) {
            editText.setError("Email is not valid!");
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }
}
