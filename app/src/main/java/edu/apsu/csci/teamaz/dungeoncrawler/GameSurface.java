package edu.apsu.csci.teamaz.dungeoncrawler;

/*
Surface the game is going to be drawn to.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Vector;

import static edu.apsu.csci.teamaz.dungeoncrawler.R.id.textView;

public class GameSurface extends View{
    //Constructors
    public GameSurface(Context context) {
        super(context);
        setup();
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public GameSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public GameSurface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    //Default setup
    private void setup(){
        //Testing code for player.
        Log.i("=============", "surface size: " + width + " " + height);
        game = new Game(new Size(1080, 1731), getContext());
    }

    //Methods

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        game.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("=============", "surface size Onmeasure: " + width + " " + height);
    }



    //Setters
    public void setUserAim(Point userAim) {
        game.setRecentUserClick(userAim);
    }

    public void startGame() {
        new GameLoop().execute();
    }

    //Game Loop
    public class GameLoop extends AsyncTask<Void, Integer, Void>{
        long time;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Size size = new Size(144,117);
            int x = width/2;
            int y = height/2;
            PlayerObject player = new PlayerObject(new Point(x,y),0, size, 0, getContext());
            game.setPlayer(player);
            //Log.i("Input Debug", player.getLocation().toString());
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(true) {
                time = System.currentTimeMillis();

                //input
                //update
                game.updateWorldObjects();
                postInvalidate();

                publishProgress();
//                try{
//                    Thread.sleep(16 - (System.currentTimeMillis() - time));
//                } catch (InterruptedException e){
//                    return null;
//                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }



    //Fields
    private int height;
    private int width;
    private Game game;
}
