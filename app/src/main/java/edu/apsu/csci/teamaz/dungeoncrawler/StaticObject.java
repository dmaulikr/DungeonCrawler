package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Size;

/*
 Base class for objects that don't move or have health. Containers will likely be a subclass.
 However, the background can be made of these.
 Collision drawable may not be needed
 */

public class StaticObject {

    //Constructor
    public StaticObject(Point location, int rotation, Size size, Context context) {
        this.mapLocation = location;
        this.renderLocation = location;
        this.rotation = rotation;
        this.size = size;
        this.context = context;
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
            canvas.translate(renderLocation.x + offset.x, renderLocation.y + offset.y);

            canvas.rotate(-rotation);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    //Getters
    public Point getMapLocation() {
        return mapLocation;
    }

    public Point getRenderLocation() { return renderLocation;}

    public int getRotation() {
        return rotation;
    }

    public Size getSize() {
        return size;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public Drawable getCollisionDrawable() {
        return collisionDrawable;
    }

    //Setters
    public void setMapLocation(Point location) {
        this.mapLocation = location;
    }

    public void setRenderLocation(Point location) {
        this.renderLocation = location;
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

    public void setCollisionDrawable(int collisionDrawableID) {
        this.drawable = ContextCompat.getDrawable(context, collisionDrawableID);
    }

    //Fields
    private Point mapLocation;
    private Point renderLocation;
    private int rotation;
    private Size size;
    private Drawable drawable;
    private Drawable collisionDrawable;
    private Context context;
}
