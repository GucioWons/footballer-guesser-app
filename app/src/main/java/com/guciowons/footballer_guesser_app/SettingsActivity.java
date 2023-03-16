package com.guciowons.footballer_guesser_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.guciowons.footballer_guesser_app.databinding.ActivitySettingsBinding;
import com.guciowons.footballer_guesser_app.presence.BaseActivity;

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
        String newUsername = Objects.requireNonNull(binding.changeUsernameEt.getEditText()).getText().toString();
        Toast.makeText(this, newUsername, Toast.LENGTH_SHORT).show();
    }

    private void changeEmail(){
        String newEmail = Objects.requireNonNull(binding.changeEmailEt.getEditText()).getText().toString();
        Toast.makeText(this, newEmail, Toast.LENGTH_SHORT).show();
    }

    private void changePassword(){
        String newPassword = Objects.requireNonNull(binding.newPasswordEt.getEditText()).getText().toString();
        Toast.makeText(this, newPassword, Toast.LENGTH_SHORT).show();
    }
}