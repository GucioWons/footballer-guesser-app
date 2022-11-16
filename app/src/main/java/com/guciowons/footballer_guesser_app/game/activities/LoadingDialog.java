package com.guciowons.footballer_guesser_app.game.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;

import com.guciowons.footballer_guesser_app.R;

public class LoadingDialog {
    private Activity activity;
    private Dialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void loadingAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_loading, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void dismissAlertDialog(){
        dialog.dismiss();
    }
}
