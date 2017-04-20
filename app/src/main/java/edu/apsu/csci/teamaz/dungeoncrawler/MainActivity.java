package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.PlayerEntity;

public class MainActivity extends AppCompatActivity {
    boolean firstRun = true;
    private MainMenuDialog mainMenuDialog;

    //gyroscope stuff

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMenuDialog = new MainMenuDialog(this, this);
        //I hid the dialog so i can test faster.
        mainMenuDialog.show();

        final GameSurface surface = (GameSurface) findViewById(R.id.gameSurface);
//        surface.addPlayer();
        surface.setOnTouchListener(new OnGameTouch(surface));

        Button moveButton = (Button) findViewById(R.id.main_menu_button);


        //Gyroscope test
        SensorManager sensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);



        //If this part isn't done delayed from onCreate it will force the player location to 0,0
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
            if (event.getAction() == MotionEvent.ACTION_DOWN
//                    || event.getAction() == MotionEvent.ACTION_UP
//                    || event.getAction() == MotionEvent.ACTION_MOVE
                    ) {
                Point clickedPoint = new Point((int) event.getX(), (int) event.getY());
                Log.i("Player Debug", clickedPoint.toString());
                surface.movePlayerTo(clickedPoint);

                return true;
            }
            return false;
        }

    }
}
