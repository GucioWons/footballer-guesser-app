package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import static org.junit.Assert.*;

import com.guciowons.footballer_guesser_app.presence.authorization.validators.UsernameValidator;

import org.junit.Test;

public class UsernameValidatorTest {

    @Test
    public void validateUsernameEmpty() {
        String response = UsernameValidator.validateUsername("");
        assertEquals("Username cannot be empty!", response);
    }

    @Test
    public void validateUsernameCorrect() {
        String response = UsernameValidator.validateUsername("username");
        assertEquals("Success", response);
    }
}