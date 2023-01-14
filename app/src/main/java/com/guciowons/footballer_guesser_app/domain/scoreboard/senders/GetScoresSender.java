package com.guciowons.footballer_guesser_app.domain.scoreboard.senders;

import com.android.volley.RequestQueue;
import com.guciowons.footballer_guesser_app.data.scoreboard.requests.ScoresRequestManager;
import com.guciowons.footballer_guesser_app.domain.scoreboard.viewmodel.ScoreboardViewModel;

public class GetScoresSender {
    public static void fetchScores(ScoreboardViewModel viewModel, String time, Integer leagueId, RequestQueue requestQueue) {
        ScoresRequestManager scoresRequestManager = new ScoresRequestManager();
        requestQueue.add(scoresRequestManager.getScores(viewModel, createUrl(time, leagueId)));
    }

    private static String createUrl(String time, Integer leagueId){
        String url = "http://footballerguesserservice-env.eba-iwqz7xzh.eu-central-1.elasticbeanstalk.com/scores?";
        if(time != null){
            url = url + "time=" + time + "&";
        }
        if(leagueId != null){
            url = url + "leagueId=" + leagueId;
        }
        return url;
    }
}
