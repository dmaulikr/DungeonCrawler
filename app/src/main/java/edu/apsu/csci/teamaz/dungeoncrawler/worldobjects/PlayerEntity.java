package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
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
    public PlayerEntity(Point location, int rotation, Size size, int step, Context context) {
        super(new Point(100,100), rotation, size, step, context);
        setRenderLocation(location);
        setDrawable(R.drawable.character);
        renderOffset = new Point();
        isMoving = false;
    }

    @Override
    public void updateRotation(Point targetPoint){
        //this is needs to be researched more
        int rotation = (int) Math.ceil(Math.toDegrees(Math.atan2(targetPoint.x - renderLocation.x, targetPoint.y - renderLocation.y)));
        if(this.rotation != rotation){
            Log.i("Player Debug", "" + rotation);
        }
        setRotation(rotation);

    }

    @Override
    public Point calculateNextLocation(int distanceModifier){
        //Log.i("=================", this.getClass().getSimpleName().toString() +  " Current Location " + mapLocation.toString());
        double scaledStep = step;
        if(numberSteps > 0 && numberSteps < 1) {
            scaledStep = step * numberSteps;
        }

        Point p = new Point(mapLocation.x, mapLocation.y);
        p.x = p.x - (int) (-(scaledStep + distanceModifier)  *  Math.sin(Math.toRadians(rotation)));
        p.y = p.y + (int) ((scaledStep + distanceModifier) *  Math.cos(Math.toRadians(-rotation)));

        //Log.i("=================", this.getClass().getSimpleName().toString() + " Rotation " + rotation);
        //Log.i("=================", this.getClass().getSimpleName().toString() +  " New Location " + p.toString());

        return p;
    }

    @Override
    public void draw(Canvas canvas, Point offset){
        if(drawable != null) {
            drawable.setBounds(0 - size_dp.getWidth() / 2, 0 - size_dp.getHeight() / 2,
                    size_dp.getWidth() / 2, size_dp.getHeight() / 2);
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            //canvas.translate(renderLocation.x, renderLocation.y);

            canvas.rotate(-rotation);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    @Override
    public String toString() {
        return "PlayerEntity Point: (" + getRenderLocation().x + "," + getRenderLocation().y + ")";
    }

    //get
    public Point getRenderLocation() {
        return renderLocation;
    }

    public Point getRenderLocation_dp() {
        return renderLocation_dp;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public double getNumberSteps() {
        return numberSteps;
    }

    //Set
    public void setRenderLocation(Point renderLocation) {
        renderLocation_dp = new Point(renderLocation);
        renderLocation_dp.x = (int)(renderLocation.x /scale_dp);
        renderLocation_dp.y = (int)(renderLocation.y /scale_dp);
        this.renderLocation = renderLocation;
    }

    public void setMoving(boolean moving) {
        this.isMoving = moving;
    }

     public void setNumberSteps(double numberSteps) {
        this.numberSteps = numberSteps;
    }

    protected Point renderLocation;
    protected Point renderLocation_dp;
    protected Point renderOffset;
    protected boolean isMoving;
    protected double numberSteps;
}

