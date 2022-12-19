package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import static org.junit.Assert.*;

import com.guciowons.footballer_guesser_app.presence.authorization.validators.PasswordValidator;

import org.junit.Test;

public class PasswordValidatorTest {

    @Test
    public void validatePasswordEmpty() {
        String response = PasswordValidator.validatePassword("");
        assertEquals("Password cannot be empty!", response);
    }

    @Test
    public void validatePasswordCorrect() {
        String response = PasswordValidator.validatePassword("password");
        assertEquals("Success", response);
    }
}