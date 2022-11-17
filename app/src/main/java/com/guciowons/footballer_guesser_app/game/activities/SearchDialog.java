package com.guciowons.footballer_guesser_app.game.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;

import com.guciowons.footballer_guesser_app.R;

public class SearchDialog {
    private Activity activity;
    private Dialog dialog;

    public SearchDialog(Activity activity) {
        this.activity = activity;
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_search, null));
        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    public void dismiss(){
        dialog.dismiss();
    }
}
