package edu.apsu.csci.teamaz.dungeoncrawler;

/*
Surface the game is going to be drawn to.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;

import java.util.Date;
import java.util.Vector;

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
        game = new Game();
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
    }



    //Setters
    public void setUserAim(Point userAim) {
//        game.get(0).updateRotation(userAim);
        game.setRecentUserClick(userAim);
        invalidate();
    }

    public void setPlayer() {
        Size size = new Size(144,117);
        int x = width/2;
        int y = height/2;
        VariableObject player = new VariableObject(new Point(x,y),0, size, 0, getContext());
        game.setPlayer(player);
        player.setDrawable(getContext().getDrawable(R.drawable.character));
        new GameLoop().execute();
    }

    //Game Loop
    public class GameLoop extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            long time;
            while(true) {
                time = System.currentTimeMillis();

                //input
                //update
                game.updateWorldObjects();
                postInvalidate();

                try{
                    Thread.sleep(33 - (System.currentTimeMillis() - time));
                } catch (InterruptedException e){
                    return null;
                }
            }
        }
    }



    //Fields
    private int height;
    private int width;
    private Game game;
}
