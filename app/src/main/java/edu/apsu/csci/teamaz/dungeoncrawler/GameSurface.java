package edu.apsu.csci.teamaz.dungeoncrawler;

/*
Surface the game is going to be drawn to.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;

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
        requestLayout();
        Size size = new Size(100,100);
        int x = 1000 / 2 - size.getWidth() /2;
        int y = 1000 / 2 - size.getHeight() / 2;
        player = new VariableObject(new Point(x,y),0, size, 0, getContext());
    }

    //Methods

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        player.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    //Setters
    public void setUserAim(Point userAim) {
        player.updateRotation(userAim);
        invalidate();
    }


    private int height;
    private int width;
    private VariableObject player;

}
