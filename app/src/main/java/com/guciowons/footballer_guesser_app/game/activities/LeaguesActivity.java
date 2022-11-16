package com.guciowons.footballer_guesser_app.game.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.game.entities.League;
import com.guciowons.footballer_guesser_app.retrofit.RequestManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LeaguesActivity extends AppCompatActivity {
    TextView id_textview, username_textview, email_textview;
    private List<League> leagues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        id_textview = findViewById(R.id.id_textview);
        username_textview = findViewById(R.id.username_textview);
        email_textview = findViewById(R.id.email_textview);
        loadLeagues();
        System.out.println(leagues);
    }

    public void loadLeagues(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.2:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        RequestManager requestManager = retrofit.create(RequestManager.class);
        getLeaguesFromCall(requestManager.getAllLeagues());
    }

    public void getLeaguesFromCall(Call<List<League>> leaguesCall){
        Thread t = new Thread(() -> leagues = (getLeaguesFromResponse(leaguesCall)));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<League> getLeaguesFromResponse(Call<List<League>> leaguesCall){
        try {
            return leaguesCall.execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}