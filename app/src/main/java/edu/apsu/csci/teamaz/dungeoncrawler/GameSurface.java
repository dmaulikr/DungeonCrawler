package edu.apsu.csci.teamaz.dungeoncrawler;

/*
Surface the game is going to be drawn to.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.view.View;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.PlayerEntity;

public class GameSurface extends View{
    //Constructors
    public GameSurface(Context context) {
        super(context);
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameSurface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //Default setup
    private void setup(){
        //Testing code for player.
        Log.i("=============", "surface size: " + width + " " + height);

        Size size = new Size(144,117);
        int x = width/2;
        int y = height/2;
        PlayerEntity player = new PlayerEntity(new Point(x,y),0, size, 25, getContext());
        player.setMapLocation(new Point(600,600));

        game = new Game(new Size(width, height), getContext(), player);
    }

    //Methods

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(game != null){
            game.draw(canvas);
        }
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

    public void setPlayerMove(boolean value){
        game.setPlayerMoving(value);
    }

    public void startGame() {
        if(gameLoop ==  null) {
            gameLoop = new GameLoop();
            gameLoop.execute();
        }
    }

    public void stopGame(){
        if(gameLoop != null){
            gameLoop.cancel(true);
            gameLoop = null;
        }
    }

    //Game Loop
    public class GameLoop extends AsyncTask<Void, Integer, Void>{
        long time;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setup();
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
                try{
                    long delay = 16 - (System.currentTimeMillis() - time);
                    if(delay > 0) {
                        Thread.sleep(delay);
                    }
                } catch (InterruptedException e){
                    return null;
                }
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
    private GameLoop gameLoop;
}
