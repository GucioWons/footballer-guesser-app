package com.guciowons.footballer_guesser_app;

import android.os.Bundle;
import android.view.View;

import com.guciowons.footballer_guesser_app.databinding.ActivitySettingsBinding;
import com.guciowons.footballer_guesser_app.presence.BaseActivity;

public class SettingsActivity extends BaseActivity {
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}