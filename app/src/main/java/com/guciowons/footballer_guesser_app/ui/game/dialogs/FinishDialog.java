package com.guciowons.footballer_guesser_app.ui.game.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.ui.game.activity.GameActivity;

public class FinishDialog {
    //TODO
    //Finish dialog binding
    private GameActivity activity;
    private String name;
    private Dialog dialog;
    private TextView nameText;
    private Button nextButton;

    public FinishDialog(GameActivity activity, String name) {
        this.activity = activity;
        this.name = name;
    }

    public void show(){
        dialog = createDialog().create();
        dialog.show();
    }

    private AlertDialog.Builder createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_finish, null);
        builder.setView(dialogView);
        builder.setCancelable(false);
        setUpViews(dialogView);
        return builder;
    }

    private void setUpViews(View dialogView){
        nameText = dialogView.findViewById(R.id.finish_name_text);
        nextButton = dialogView.findViewById(R.id.next_button);
        nameText.setText(nameText.getText() + " " + name);
        nextButton.setOnClickListener(view -> {
            dialog.dismiss();
            activity.recreate();
        });
    }
}
