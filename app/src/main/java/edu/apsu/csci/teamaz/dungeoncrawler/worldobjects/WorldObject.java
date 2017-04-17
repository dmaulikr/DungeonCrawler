package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Size;

/**
 * Base class for objects that don't move or have health and the bare minimum for objects that are
 * drawn on screen.
 */

public class WorldObject {

    //Constructor
    public WorldObject(Point location, int rotation, Size size, Context context, boolean isPassable) {
        this.mapLocation = location;
        this.rotation = rotation;
        this.size = size;
        this.context = context;
        this.isPassable = isPassable;
    }

    //Methods
    public void draw(Canvas canvas, Point offset){
        if(offset == null){
            offset = new Point(0,0);
        }

        if(drawable != null) {
            drawable.setBounds(0 - size.getWidth() / 2, 0 - size.getHeight() / 2,
                    size.getWidth() / 2, size.getHeight() / 2);
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            canvas.translate(mapLocation.x + offset.x, mapLocation.y + offset.y);

            canvas.rotate(-rotation);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    //Getters
    public Point getMapLocation() {
        return mapLocation;
    }

    public int getRotation() {
        return rotation;
    }

    public Size getSize() {
        return size;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public boolean isPassable() {
        return isPassable;
    }

    //Setters
    public void setMapLocation(Point location) {
        this.mapLocation = location;
    }

    public void setRotation(int rotation) {
        while(rotation < 0 || rotation > 360) {
            if (rotation > 360) {
                rotation = rotation - 360;
            } else if (rotation < 0) {
                rotation = rotation + 360;
            }
        }
        this.rotation = rotation;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setDrawable(int drawableID) {
        this.drawable = ContextCompat.getDrawable(context, drawableID);
    }

    public void setPassable(boolean passable) {
        isPassable = passable;
    }

    //Fields
    protected Point mapLocation;
    protected int rotation;
    protected Size size;
    protected Drawable drawable;
    protected Context context;
    protected boolean isPassable;
}
