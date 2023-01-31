package com.guciowons.footballer_guesser_app.presence.game.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;

import com.guciowons.footballer_guesser_app.databinding.DialogFinishBinding;
import com.guciowons.footballer_guesser_app.presence.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.presence.leagues.activities.LeaguesActivity;

public class FinishDialog {
    private DialogFinishBinding binding;
    private final GameActivity activity;
    private final String name;
    private Dialog dialog;

    public FinishDialog(GameActivity activity, String name) {
        this.activity = activity;
        this.name = name;
    }

    public void show(){
        dialog = createDialog().create();
        dialog.show();
    }

    private AlertDialog.Builder createDialog(){
        binding = DialogFinishBinding.inflate(activity.getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(binding.getRoot());
        builder.setCancelable(false);
        setUpViews();
        return builder;
    }



    private void setUpViews(){
        binding.finishNameText.setText(binding.finishNameText.getText() + " " + name);
        binding.nextButton.setOnClickListener(view -> recreateActivity());
        binding.backButton.setOnClickListener(view -> goBack());
    }

    private void recreateActivity(){
        dialog.dismiss();
        activity.recreate();
    }

    private void goBack(){
        dialog.dismiss();
        Intent intent = new Intent(activity, LeaguesActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
