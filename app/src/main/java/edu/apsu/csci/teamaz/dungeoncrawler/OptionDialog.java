package edu.apsu.csci.teamaz.dungeoncrawler;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

/**
 *
 */

public class OptionDialog {

        private Context context;
        private MainActivity mainActivity;
        OptionDialog(final Context context, final MainActivity mainActivity){
            this.context = context;
            this.mainActivity = mainActivity;
        }
        public void show(){
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.activity_option_menu);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();

            dialog.findViewById(R.id.cancel_button_options).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    new MainMenuDialog(context,mainActivity).show();
                }
            });


        }
    }

