package edu.apsu.csci.teamaz.dungeoncrawler;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * This creates an object to display the Option Menu
 */

public class OptionDialog {

        private Context context;//Used to display the dialog
        private MainActivity mainActivity;//Used to create a menu object
        private double zoom;//Used to save the final zoom number
        private double tempZoom;//Used to keep track of the current zoom on the seeker bar

        /*Constructor*/
        OptionDialog(final Context context, final MainActivity mainActivity){
            this.context = context;
            this.mainActivity = mainActivity;
            zoom = 1;
        }

        /*This method is called to display the main menu*/
        public void show(){
            final Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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

            final SeekBar seekBar = (SeekBar) dialog.findViewById(R.id.seekerbar_zoom);

            /*This sets up the confirm button,
             *which when press will save and update the game to the newest setting */
            dialog.findViewById(R.id.confirm_button_options).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainActivity.zoom = zoom;
                    dialog.cancel();
                    zoom = tempZoom/100.0;
                    new MainMenuDialog(context,mainActivity).show();
                }
            });


            /*Gets the textview associated with the zoom feature to display a numeric
             *value when the slider shifts */
            final TextView tv = (TextView) dialog.findViewById(R.id.textView_zoom);

            /*Set  up the seeker bar associated with the zoom feature*/
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(i < 150){
                        i += 50;
                    }
                    tempZoom = i;
                    String num = Integer.toString(i);
                    tv.setText(num);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }
    }

