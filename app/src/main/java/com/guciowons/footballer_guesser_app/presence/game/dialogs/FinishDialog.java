package com.guciowons.footballer_guesser_app.presence.game.dialogs;

import android.app.Dialog;
import android.content.Intent;

import com.guciowons.footballer_guesser_app.databinding.DialogFinishBinding;
import com.guciowons.footballer_guesser_app.presence.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.presence.leagues.activities.LeaguesActivity;

//Set Dialog bigger
public class FinishDialog {
    private DialogFinishBinding binding;
    private GameActivity activity;
    private String name;
    private Dialog dialog;

    public FinishDialog(GameActivity activity, String name) {
        this.activity = activity;
        this.name = name;
    }

    public void show(){
        dialog = createDialog();
        dialog.show();
    }

    private Dialog createDialog(){
        binding = DialogFinishBinding.inflate(activity.getLayoutInflater());
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(binding.getRoot());
        setUpViews();
        return dialog;
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
        Intent intent = new Intent(activity, LeaguesActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
