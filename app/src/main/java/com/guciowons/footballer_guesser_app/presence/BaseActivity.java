package com.guciowons.footballer_guesser_app.presence;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.domain.BaseViewModel;
import com.guciowons.footballer_guesser_app.presence.authorization.landing.activities.LandingActivity;
import com.guciowons.footballer_guesser_app.presence.loading.dialogs.LoadingDialog;

public abstract class BaseActivity extends AppCompatActivity {
    protected LoadingDialog loadingDialog;

    protected void setUpMenu(MaterialToolbar toolbar, BaseViewModel scoreboardViewModel){
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout){
                logoutUser(scoreboardViewModel);
            }
            return true;
        });
    }

    protected void logoutUser(BaseViewModel viewModel){
        viewModel.logoutUser();
        Intent intent = new Intent(BaseActivity.this, LandingActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    protected void setUpErrorObserver(BaseViewModel viewModel){
        viewModel.getError().observe(this,
                error -> Toast.makeText(this, error, Toast.LENGTH_SHORT).show());
    }

    protected void startLoadingDialog(){
        loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
    }

}
