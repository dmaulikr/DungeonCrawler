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
        entities = new Vector<>();
    }

    //Methods

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(entities.size() > 0) {
            entities.get(0).draw(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }



    //Setters
    public void setUserAim(Point userAim) {
        entities.get(0).updateRotation(userAim);
        invalidate();
    }

    public void setPlayer() {
        Size size = new Size(150,75);
        int x = width/2 - size.getWidth() /2;
        int y = height/2 - size.getHeight() / 2;
        entities.add(new PlayerObject(new Point(x,y),0, size, 0, getContext()));
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
                updateWorldObjects();
                postInvalidate();

                try{
                    Thread.sleep(33 - (System.currentTimeMillis() - time));
                } catch (InterruptedException e){
                    return null;
                }
            }
        }

        private void updateWorldObjects() {
            for(VariableObject object: entities){
                object.updateLocation();
            }
        }
    }



    //Fields
    private int height;
    private int width;
    private Vector<VariableObject> entities;
}
