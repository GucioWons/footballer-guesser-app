package com.guciowons.footballer_guesser_app.presence.authorization.validators;

import com.guciowons.footballer_guesser_app.presence.authorization.validators.helper.StringHelper;

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
