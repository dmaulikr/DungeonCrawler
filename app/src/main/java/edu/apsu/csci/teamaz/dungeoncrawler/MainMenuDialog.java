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
 * Creates an object which displays the main menu
 */

public class MainMenuDialog{
    /*Context is needed to display the dialog for the current activity*/
    private Context context;
    /*The MainActivity is needed to close the activity at the users discretion */
    private MainActivity mainActivity;

    /*Constructor*/
    MainMenuDialog(final Context context, final MainActivity mainActivity){
        this.context = context;
        this.mainActivity = mainActivity;
    }

    /*This method is called to display the main menu*/
    public void show(){
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.activity_main_menu);

        /*
            This creates the rounded corners of the main menu dialog,
            which are transparent to begin with
        */
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();

        /*Get the  Buttons to setup*/
        Button start_button = (Button) dialog.findViewById(R.id.button_start_main_menu);
        final Button option_button = (Button) dialog.findViewById(R.id.button_option_main_menu);
        Button exit_button = (Button) dialog.findViewById(R.id.button_exit_main_menu);

        /*Start Button OnClickListener*/
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        /*Option Button OnClickListener*/
        option_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new OptionDialog(context,mainActivity).show();

            }
        });

        /*Exit Button OnClickListener*/
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Sets up a dialog to confirm the user wants to close the app*/
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
