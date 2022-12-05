package com.guciowons.footballer_guesser_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.guciowons.footballer_guesser_app.authentication.activities.SignInActivity;
import com.guciowons.footballer_guesser_app.authentication.activities.SignUpActivity;

public class LandingActivity extends AppCompatActivity {
    private Button signInBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        signInBtn = findViewById(R.id.sign_in_btn);
        signUpBtn = findViewById(R.id.sign_up_btn);

        signInBtn.setOnClickListener(view -> goToSignIn());
        signUpBtn.setOnClickListener(view -> goToSignUp());
    }

    public void goToSignIn(){
        Intent intent = new Intent(LandingActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void goToSignUp(){
        Intent intent = new Intent(LandingActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}