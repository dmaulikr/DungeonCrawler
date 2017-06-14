package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;

import edu.apsu.csci.teamaz.dungeoncrawler.R;

/**
 * The PlayerEntity class is a subclass of the GenericEntity class. It serves primarily as a means of
 * keeping track and drawing the player on screen.
 *
 * Render location should always bee the center of the screen.
 */

public class PlayerEntity extends GenericEntity {
    protected Point renderLocation;
    protected Point renderLocation_dp;
    protected boolean isMoving;
    protected double numberSteps;

    /* Constructor(s) */
    /***********************/
    public PlayerEntity(Point location, int rotation, Size size, int step, Context context) {
        super(new Point(100,100), rotation, size, step, context);
        this.renderLocation = new Point();
        this.renderLocation_dp = new Point();
        isMoving = false;
        numberSteps = 0;

        setDrawableByID(R.drawable.character);
        setRenderLocation(location);
    }

    /* Method(s) */
    /***********************/
    /* Sets in motion the events needed to move the player to 'targetPoint' */
    public void moveTo(Point targetPoint){
        updateRotation(targetPoint);
        setMoving(true);
        setNumberSteps(calcNumSteps(targetPoint));
    }

    /* Gets the number of player steps using the distance formula. */
    private double calcNumSteps(Point targetPoint){
        int x = targetPoint.x - getRenderLocation().x;
        int y = targetPoint.y - getRenderLocation().y;

        double distance = Math.abs(Math.sqrt(x*x + y*y)) / getScale_dp();
        //Log.i("Distance between p1/p2", "" + distance);
        return  distance / getStep();
    }


    /* Override Method(s) */
    /***********************/
    /* Updates the rotation of the player.
    *  Overridden to make use of the render location instead of the map location.
    */
    @Override
    public void updateRotation(Point targetPoint){
        //this is needs to be researched more
        int rotation = (int) Math.ceil(Math.toDegrees(Math.atan2(targetPoint.x - renderLocation.x, targetPoint.y - renderLocation.y)));
        if(this.rotation != rotation){
            Log.i("Player Debug", "" + rotation);
        }
        setRotation(rotation);

    }

    /* Calculates the next location of the player.
     *  Overridden to make use of the render location instead of the map location.
     */
    @Override
    public Point calculateNextLocation(int distanceModifier){
        double scaledStep = step;
        if(numberSteps > 0 && numberSteps < 1) {
            scaledStep = step * numberSteps;
        }

        Point p = new Point(mapLocation.x, mapLocation.y);
        p.x = p.x - (int) (-(scaledStep + distanceModifier)  *  Math.sin(Math.toRadians(rotation)));
        p.y = p.y + (int) ((scaledStep + distanceModifier) *  Math.cos(Math.toRadians(-rotation)));

        return p;
    }

    /* Its toString plain and simple. */
    @Override
    public String toString() {
        return "PlayerEntity Point: (" + getRenderLocation().x + "," + getRenderLocation().y + ")";
    }

    /* Getters and Setters */
    /***********************/
    /* The location where the player is drawn. This is usually the center of the screen. */
    public void setRenderLocation(Point newRenderLocation) {
        setRenderLocation_dp(newRenderLocation);
        this.renderLocation = newRenderLocation;
    }

    public Point getRenderLocation() {
        return renderLocation;
    }

    /* Scaled render location for the player. */
    public void setRenderLocation_dp(Point newRenderLocation) {
        renderLocation_dp.x = (int)(newRenderLocation.x / getScale_dp());
        renderLocation_dp.y = (int)(newRenderLocation.y / getScale_dp());

    }

    public Point getRenderLocation_dp() {
        return renderLocation_dp;
    }

    /* If true, the player is moving else the player is not. */
    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    /* The number of steps the player takes to get to a target point. */
    public void setNumberSteps(double numberSteps) {
        this.numberSteps = numberSteps;
    }

    public double getNumberSteps() {
        return numberSteps;
    }
}

