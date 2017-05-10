package edu.apsu.csci.teamaz.dungeoncrawler;

/*
Surface the game is going to be drawn to.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Size;
import android.view.View;


public class GameSurface extends View{
    private int height;
    private int width;
    private Game game;
    private GameLoop gameLoop;

    /* Constructor(s) */
    /***********************/
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

    /* Default View Setup */
    /***********************/
    private void setup(){
        //Testing code for player.
        Log.i("=============", "surface size: " + width + " " + height);

        Size size = new Size(240,195);
        int x = width/2;
        int y = height/2;
        //update
        game = new Game(size, getContext());
    }

    /* Methods(s) */
    /***********************/
    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setStyle(Paint.Style.FILL);
//        background.setColor(0xff231400);
        background.setColor(Color.BLACK);
        super.onDraw(canvas);

        canvas.drawPaint(background);
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

    /* Tells the player to move to 'targetPoint' */
    protected void movePlayerTo(Point targetPoint){
        game.movePlayerTo(targetPoint);
    }

    /* Starts and stops the game loop */
    public void startGame() {
        if(gameLoop ==  null) {
            gameLoop = new GameLoop();
            gameLoop.execute();
        }
    }

    /*Used to cancel the game async task*/
    public void stopGame(){
        if(gameLoop != null){
            gameLoop.cancel(true);
            gameLoop = null;
        }
    }

    /* Game Loop */
    /***********************/
    public class GameLoop extends AsyncTask<Void, Integer, Void>{
        long time;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //update
            setup();
            //Log.i("Input Debug", player.getLocation().toString());
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(true) {
                time = System.currentTimeMillis();

                //input

                game.updateWorldObjects();
                postInvalidate();

                publishProgress();
                try{
                    long delay = 16 - (System.currentTimeMillis() - time);
                    if(delay > 0) {
                        Thread.sleep(delay);
                    } else {
                        Log.i("Game Loop Debug", "Loop went over by " + delay + " milliseconds");
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
}
