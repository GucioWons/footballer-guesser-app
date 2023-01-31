package com.guciowons.footballer_guesser_app.presence.loading.dialogs;

import android.app.Activity;
import android.app.Dialog;

import com.guciowons.footballer_guesser_app.databinding.DialogLoadingBinding;

public class LoadingDialog {
    private final Activity activity;
    private Dialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void show(){
        dialog = createDialog();
        dialog.show();
    }

    private Dialog createDialog(){
        DialogLoadingBinding binding = DialogLoadingBinding.inflate(activity.getLayoutInflater());
        dialog = new Dialog(activity);
        dialog.setCancelable(false);
        dialog.setContentView(binding.getRoot());
        return dialog;
    }

    public void dismiss(){
        dialog.dismiss();
    }
}
