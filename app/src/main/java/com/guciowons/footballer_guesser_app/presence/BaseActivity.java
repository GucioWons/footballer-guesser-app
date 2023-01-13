package com.guciowons.footballer_guesser_app.presence;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.domain.BaseViewModel;
import com.guciowons.footballer_guesser_app.presence.authorization.landing.activities.LandingActivity;
import com.guciowons.footballer_guesser_app.presence.scoreboard.activities.ScoreboardActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected void setUpErrorObserver(BaseViewModel viewModel){
        viewModel.getError().observe(this,
                error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show());
    }

    protected void logoutUser(BaseViewModel viewModel){
        viewModel.logoutUser();
        Intent intent = new Intent(BaseActivity.this, LandingActivity.class);
        startActivity(intent);
        finishAffinity();
    }

}
