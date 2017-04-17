package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Point;
import android.util.Size;

/*
 Base class for objects that move or have health.
 */

public class Entity extends WorldObject {
    //Constructor
    //Context will be removed later as the object is only going to store the drawable itself.
    public Entity(Point location, int rotation, Size size, int step, Context context) {
        super(location,rotation,size, context);
        this.step = step;
        this.context = context;

    }

    //Methods
    //moves the object
    public void updateLocation(){}

    public void updateRotation(Point targetPoint){
        //this is needs to be researched more
        setRotation((int) Math.toDegrees(Math.atan2(targetPoint.x - getRenderLocation().x, targetPoint.y - getRenderLocation().y)));

    }

    //Getters
    public int getReverseCount() {
        return reverseCount;
    }

    public int getReverseStep() {
        return reverseStep;
    }

    public int getStep() {
        return step;
    }


    //Setters
    public void setStep(int step) {
        this.step = step;
    }


    public void setReverseCount(int reverseCount) {
        this.reverseCount = reverseCount;
    }

    public void setReverseStep(int reverseStep) {
        this.reverseStep = reverseStep;
    }


    //Fields
    private int step;
    private int reverseCount;
    private int reverseStep;
    private Context context;
}
