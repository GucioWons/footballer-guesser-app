package com.guciowons.footballer_guesser_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.guciowons.footballer_guesser_app.databinding.ActivitySettingsBinding;
import com.guciowons.footballer_guesser_app.presence.BaseActivity;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.DefaultTextValidator;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.EmailValidator;
import com.guciowons.footballer_guesser_app.presence.authorization.validators.PasswordValidator;

import org.w3c.dom.Text;

import java.util.Objects;

public class SettingsActivity extends BaseActivity {
    private SettingsViewModel settingsViewModel;
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setUpViewModel();
        setUpButtons();
    }

    private void setUpViewModel(){
        settingsViewModel = new SettingsViewModel(getApplication());
    }

    private void setUpButtons(){
        binding.changeEmailBtn.setOnClickListener(view -> changeEmail());
        binding.changeUsernameBtn.setOnClickListener(view -> changeUsername());
        binding.changePasswordBtn.setOnClickListener(view -> changePassword());
    }

    private void changeUsername(){
        TextInputLayout usernameInput = binding.changeUsernameEt;
        String newUsername = Objects.requireNonNull(binding.changeUsernameEt.getEditText()).getText().toString();
        validateField(usernameInput, DefaultTextValidator.validateText(newUsername, "Username"));
    }

    private void changeEmail(){
        TextInputLayout emailInput = binding.changeEmailEt;
        String newEmail = Objects.requireNonNull(emailInput.getEditText()).getText().toString();
        validateField(emailInput, EmailValidator.validateEmail(newEmail));
    }

    private void changePassword(){
        TextInputLayout passwordInput = binding.newPasswordEt;
        String newPassword = Objects.requireNonNull(passwordInput.getEditText()).getText().toString();
        validateField(passwordInput, PasswordValidator.validatePassword(newPassword));
    }

    private void validateField(TextInputLayout input, String validationMessage){
        if(validationMessage.equals("Success")){
            Objects.requireNonNull(input.getEditText()).setText("");
            input.setError(null);
            Toast.makeText(this, "Works", Toast.LENGTH_SHORT).show();
        }else{
            input.setError(validationMessage);
        }
    }
}