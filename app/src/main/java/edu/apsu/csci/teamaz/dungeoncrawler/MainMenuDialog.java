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
 * Created by Amy on 3/28/2017.
 */

public class MainMenuDialog {
    private Context context;
    private MainActivity mainActivity;
    MainMenuDialog(final Context context, final MainActivity mainActivity){
        this.context = context;
        this.mainActivity = mainActivity;
    }
    public void show(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_main_menu);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();

        Button start_button = (Button) dialog.findViewById(R.id.button_start_main_menu);
        Button option_button = (Button) dialog.findViewById(R.id.button_option_main_menu);
        Button exit_button = (Button) dialog.findViewById(R.id.button_exit_main_menu);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        option_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.dismiss();
                                mainActivity.finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.show();
                            }
                        });
                builder.create().show();
            }

        });
    }
}
