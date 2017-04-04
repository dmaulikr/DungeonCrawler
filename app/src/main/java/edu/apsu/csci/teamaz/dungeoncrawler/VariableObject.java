package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Size;

/*
 Base class for objects that move or have health.
 */

public class VariableObject extends StaticObject{
    //Constructor
    //Context will be removed later as the object is only going to store the drawable itself.
    public VariableObject(Point location, int rotation, Size size, int step, Context context) {
        super(location,rotation,size);
        this.location = location;
        this.rotation = rotation;
        this.size = size;
        this.step = step;
        this.context = context;
    }

    //Methods
    //draws the object on the canvas.
    public void updateLocation(){}

    public void updateRotation(Point targetPoint){
        //this is needs to be researched more
        rotation = (int) Math.toDegrees(Math.atan2(targetPoint.x - location.x, targetPoint.y - location.y));
    }


    //Getters
    public int getReverseCount() {
        return reverseCount;
    }

    public int getReverseStep() {
        return reverseStep;
    }

    //Setters
    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
//        GradientDrawable background = new GradientDrawable();
//        background.setStroke(10, ContextCompat.getColor(context, R.color.colorPrimaryDark));
//        this.drawable = background;
    }

    public void setCollisionDrawable(Drawable collisionDrawable) {
        this.collisionDrawable = collisionDrawable;
    }

    public void setReverseCount(int reverseCount) {
        this.reverseCount = reverseCount;
    }

    public void setReverseStep(int reverseStep) {
        this.reverseStep = reverseStep;
    }

    //Fields
    private Point location;
    private int rotation;
    private Size size;
    private int step;
    private Drawable drawable;
    private Drawable collisionDrawable;
    private int reverseCount;
    private int reverseStep;
    private Context context;
}
