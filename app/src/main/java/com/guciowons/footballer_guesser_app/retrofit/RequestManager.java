package com.guciowons.footballer_guesser_app.retrofit;

import com.guciowons.footballer_guesser_app.game.entities.League;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestManager {
    @GET("leagues")
    Call<List<League>> getAllLeagues();
}
