package com.guciowons.footballer_guesser_app.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.guciowons.footballer_guesser_app.R;

import org.json.JSONArray;

public class LeaguesActivity extends AppCompatActivity {
    TextView id_textview, username_textview, email_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        id_textview = findViewById(R.id.id_textview);
        username_textview = findViewById(R.id.username_textview);
        email_textview = findViewById(R.id.email_textview);
        loadData();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Account", MODE_PRIVATE);
        id_textview.setText(id_textview.getText() + Integer.toString(sharedPreferences.getInt("id", 0)));
        username_textview.setText(username_textview.getText() + sharedPreferences.getString("username", ""));
        email_textview.setText(email_textview.getText() + sharedPreferences.getString("email", ""));
    }
}