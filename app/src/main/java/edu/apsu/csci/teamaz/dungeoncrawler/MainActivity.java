package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    boolean firstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameSurface surface = (GameSurface) findViewById(R.id.gameSurface);
//        surface.addPlayer();
        surface.setOnTouchListener(new OnGameTouch(surface));

    }

    public class OnGameTouch implements View.OnTouchListener {
        private GameSurface surface;
        public OnGameTouch(GameSurface surface){
            this.surface = surface;

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(firstRun){
                surface.setPlayer();
                surface.setOnTouchListener(new OnGameTouch(surface));
                firstRun = false;
            }
            if(event.getAction()  == MotionEvent.ACTION_DOWN
            || event.getAction()  == MotionEvent.ACTION_UP
            || event.getAction()  == MotionEvent.ACTION_MOVE)
            {
                surface.setUserAim(new Point( (int)event.getX(), (int) event.getY()));
                return  true;
            }
            return false;
        }
    }
}
