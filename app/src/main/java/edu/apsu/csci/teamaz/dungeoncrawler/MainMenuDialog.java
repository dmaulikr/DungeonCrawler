package edu.apsu.csci.teamaz.dungeoncrawler;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Amy on 3/28/2017.
 */

public class MainMenuDialog {
    MainMenuDialog(final Context context, final MainActivity mainActivity){
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
                dialog.cancel();
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
                dialog.cancel();
                mainActivity.finish();
            }
        });



    }
}
