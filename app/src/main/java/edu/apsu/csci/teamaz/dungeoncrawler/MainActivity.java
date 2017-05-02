package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static double zoom = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainMenuDialog mainMenuDialog = new MainMenuDialog(this, this);
        mainMenuDialog.show();

        findViewById(R.id.main_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainMenuDialog.show();
            }
        });

        final GameSurface surface = (GameSurface) findViewById(R.id.gameSurface);

        surface.setOnTouchListener(new OnGameTouch(surface));

        /*If this part isn't done delayed from onCreate it will force the player location to 0,0*/
        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        surface.startGame();
                    }
                },
                1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.i("Back Pressed", "Back was pressed.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((GameSurface) findViewById(R.id.gameSurface)).stopGame();
    }

    public class OnGameTouch implements View.OnTouchListener {
        private GameSurface surface;

        public OnGameTouch(GameSurface surface) {
            this.surface = surface;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Point clickedPoint = new Point((int) event.getX(), (int) event.getY());
                Log.i("Player Debug", clickedPoint.toString());
                surface.movePlayerTo(clickedPoint);

                return true;
            }
            return false;
        }

    }
}
