package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Size;

import edu.apsu.csci.teamaz.dungeoncrawler.MainActivity;
import edu.apsu.csci.teamaz.dungeoncrawler.Room;

/**
 * Base class for objects that don't move or have health and the bare minimum for objects that are
 * drawn on screen.
 */

public class WorldObject {
    protected Point mapLocation;
    protected Point mapLocation_dp;
    protected int rotation;
    protected Size size;
    protected Size size_dp;
    protected Drawable drawable;
    protected Context context;
    protected boolean isPassable;
    protected float scale_dp;



    /* Constructor(s) */
    /***********************/
    public WorldObject(Point location, int rotation, Size size, Context context, boolean isPassable) {
        this.mapLocation = new Point();
        this.mapLocation_dp = new Point();
        this.rotation = rotation;
        this.size= new Size(0,0);
        this.context = context;
        this.isPassable = isPassable;
        this.scale_dp = (float)MainActivity.zoom;

        /* Calculating the dp scale for the object. */
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        if (metrics.widthPixels < metrics.heightPixels) {
            setScale_dp((float) (metrics.heightPixels * .2 / Room.SIZE));
        } else {
            setScale_dp((float) (metrics.widthPixels * .2 / Room.SIZE));
        }

        setSize(size);
        setMapLocation(location);
    }

    /* Method(s) */
    /***********************/
    /* Draws the objects drawable on the given canvas where the offset is the player location. */
    public void draw(Canvas canvas, Point offset) {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);

        if(offset != null){
            canvas.translate(mapLocation_dp.x - offset.x + size_dp.getWidth() / 2,
                    mapLocation_dp.y - offset.y + size_dp.getHeight() / 2);
        }

        canvas.rotate(-rotation);
        drawable.draw(canvas);
        canvas.restore();
    }

    /* Getters and Setters */
    /***********************/
    /* The actual location on the abstract map. */
    public Point getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(Point location) {
        this.mapLocation = location;
        setMapLocation_dp(location);
    }

    /* The actual location on the abstract map converted to scale.*/
    private void setMapLocation_dp(Point location){
        mapLocation_dp.x = (int) Math.ceil(location.x * getScale_dp());
        mapLocation_dp.y = (int) Math.ceil(location.y * getScale_dp());
    }

    public Point getMapLocation_dp() {
        return mapLocation_dp;
    }

    /* Orientation on map. */
    public void setRotation(int rotation) {
        while (rotation < 0 || rotation > 360) {
            if (rotation > 360) {
                rotation = rotation - 360;
            } else if (rotation < 0) {
                rotation = rotation + 360;
            }
        }
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    /* Size of the object on the map. */
    public void setSize(Size size) {
        this.size = size;
        setSize_dp(size);
    }

    public Size getSize() {
        return size;
    }

    /* Scaled size of the object on the map. */
    private void setSize_dp(Size size){
        size_dp = new Size((int) Math.ceil(size.getWidth() * scale_dp) + 1, (int) Math.ceil(size.getHeight() * scale_dp) + 1);
    }

    public Size getSize_dp(){
        return size_dp;
    }

    /* The image that is drawn on the map. */
    public void setDrawableByID(int drawableID) {
        this.drawable = ContextCompat.getDrawable(context, drawableID);
        Point tlPoint = new Point(0 - getSize_dp().getWidth() / 2, 0 - getSize_dp().getHeight() / 2);
        Point brPoint = new Point(getSize_dp().getWidth() / 2, getSize_dp().getHeight() / 2);

        drawable.setBounds(tlPoint.x, tlPoint.y, brPoint.x, brPoint.y);
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    /* Scale factor for pixels to device pixels. */
    public void setScale_dp(float scale_dp) {
        this.scale_dp = scale_dp;
    }

    public float getScale_dp() {
        return scale_dp;
    }

    /* Collision for the object. */
    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    public boolean isPassable() {
        return isPassable;
    }
}
