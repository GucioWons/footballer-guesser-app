package com.guciowons.footballer_guesser_app.ui.game.dialogs;

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

    public void show(){
        dialog = createDialog().create();
        dialog.show();
    }

    private AlertDialog.Builder createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_loading, null));
        builder.setCancelable(false);
        return builder;
    }

    public void dismiss(){
        dialog.dismiss();
    }
}
