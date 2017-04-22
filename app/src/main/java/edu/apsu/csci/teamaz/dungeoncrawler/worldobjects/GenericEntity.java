package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;

/**
 * The GenericEntity class is a subclass of the basic WorldObject. Its serves as the primary class for
 * objects that need to be drawn and can move.
 */

public class GenericEntity extends WorldObject {
    protected int step;

    /* Constructor(s) */
    /***********************/
    public GenericEntity(Point location, int rotation, Size size, int step, Context context) {
        super(location,rotation,size, context, false);
        this.step = step;
    }

    /* Method(s) */
    /***********************/
    /* Updates where the entity is looking. */
    public void updateRotation(Point targetPoint){
        /* ArcTan is used to calculate the rotation angle. */
        int rotation = (int) Math.toDegrees(Math.atan2(targetPoint.x - mapLocation.x, targetPoint.y - mapLocation.y));

        setRotation(rotation);

    }

    /* Calculates the next location the entity will move to. */
    public Point calculateNextLocation(int distanceModifier){
        Point p = new Point(mapLocation.x, mapLocation.y);

        /* Sin and Cos are used to calculate the x and y respectively.
         * Rotation is reversed to account for the flipped y axis.
         * Since the y axis is flipped, technically x is reversed too so step for x is inverted.
         */
        p.x = p.x - (int) (-(step + distanceModifier)  *  Math.sin(Math.toRadians(rotation)));
        p.y = p.y + (int) ((step + distanceModifier) *  Math.cos(Math.toRadians(-rotation)));
        return p;
    }

    /* Getters and Setters */
    /***********************/
    /* The distance the entity moves. */
    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
