package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfirmPasswordValidatorTest {

    @Test
    public void validateConfirmPasswordEmpty() {
        String response = ConfirmPasswordValidator.validateConfirmPassword("password", "");
        assertEquals("You have to confirm password!", response);
    }

    @Test
    public void validateConfirmPasswordNotCorrect() {
        String response = ConfirmPasswordValidator.validateConfirmPassword("password", "password1");
        assertEquals("Confirm password has to be the same as password!", response);
    }

    @Test
    public void validateConfirmPasswordCorrect() {
        String response = ConfirmPasswordValidator.validateConfirmPassword("password", "password");
        assertEquals("Success", response);
    }
}