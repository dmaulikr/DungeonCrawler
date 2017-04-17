package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    boolean firstRun = true;
    MainMenuDialog mainMenuDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainMenuDialog mainMenuDialog = new MainMenuDialog(this, this);
        //I hid the dialog so i can test faster.
        //mainMenuDialog.show();

        final GameSurface surface = (GameSurface) findViewById(R.id.gameSurface);
//        surface.addPlayer();
        surface.setOnTouchListener(new OnGameTouch(surface));

        Button moveButton = (Button) findViewById(R.id.move_button);
        moveButton.setOnTouchListener(new View.OnTouchListener() {
                                          @Override
                                          public boolean onTouch(View view, MotionEvent motionEvent) {
                                              if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                                  surface.setPlayerMove(true);
                                              } else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                                                  surface.setPlayerMove(false);
                                              }
                                              return true;
                                          }
                                      }


        );

        //If this part isn't done delayed from onCreate it will force the player location to 0,0
        Handler handler = new Handler();
        handler.postDelayed(new

                                    Runnable() {
                                        @Override
                                        public void run() {
                                            surface.startGame();
                                        }
                                    }

                , 1000);
    }

    public class OnGameTouch implements View.OnTouchListener {
        private GameSurface surface;

        public OnGameTouch(GameSurface surface) {
            this.surface = surface;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN
                    || event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_MOVE) {
                Point clickedPoint = new Point((int) event.getX(), (int) event.getY());
                surface.setUserAim(clickedPoint);
                //Log.i("Player Debug", clickedPoint.toString());
                return true;
            }
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
