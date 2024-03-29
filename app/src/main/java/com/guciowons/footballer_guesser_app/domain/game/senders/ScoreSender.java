package com.guciowons.footballer_guesser_app.domain.game.senders;

import com.android.volley.RequestQueue;
import com.guciowons.footballer_guesser_app.data.game.requests.score.SendScoreRequestManager;

public class ScoreSender {
    public static void sendScore(int leagueId, int playerId, int trials, RequestQueue requestQueue) {
        if(playerId != 0){
            int points;
            if(trials <= 10){
                points = 8;
            }else if(trials <= 15){
                points = 5;
            }else if(trials <= 20){
                points = 3;
            }else{
                points = 1;
            }
            SendScoreRequestManager sendScoreRequestManager = new SendScoreRequestManager();
            requestQueue.add(sendScoreRequestManager.sendScoreRequest(createUrl(leagueId, playerId, points)));
        }
    }

    private static String createUrl(int leagueId, int playerId, int points){
        return "http://footballerguesserservice-env.eba-iwqz7xzh.eu-central-1.elasticbeanstalk.com/scores?userId=" + playerId + "&leagueId=" + leagueId + "&points=" + points;
    }
}
