package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import android.widget.EditText;

import com.guciowons.footballer_guesser_app.ui.authentication.helpers.StringHelper;

public class EmailValidator {
    public static String validateEmail(String email) {
        StringHelper stringHelper = new StringHelper();
        if (email.isEmpty()) {
            return "Email cannot be empty!";
        } else if (!stringHelper.validateEmail(email)) {
            return "Email is not valid!";
        } else {
            return "Success";
        }
    }
}
