package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Size;

/*
 Base class for objects that don't move or have health. Containers will likely be a subclass.
 However, the background can be made of these.
 */

public class StaticObject {

    //Constructor
    public StaticObject(Point location, int rotation, Size size) {
        this.location = location;
        this.rotation = rotation;
        this.size = size;
    }

    //Methods
    public void draw(Canvas canvas, Point offset){
        if(offset == null){
            offset = new Point(0,0);
        }
        drawable.setBounds(0 - size.getWidth()/2,0 - size.getHeight()/2,
                size.getWidth()/2,size.getHeight()/2);
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.translate(location.x - offset.x,location.y - offset.y);

        canvas.rotate(-rotation);
        drawable.draw(canvas);
        canvas.restore();
    }

    //Getters
    public Point getLocation() {
        return location;
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

    public Drawable getCollisionDrawable() {
        return collisionDrawable;
    }

    //Setters
    public void setLocation(Point location) {
        this.location = location;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setCollisionDrawable(Drawable collisionDrawable) {
        this.collisionDrawable = collisionDrawable;
    }

    //Fields
    private Point location;
    private int rotation;
    private Size size;
    private Drawable drawable;
    private Drawable collisionDrawable;
}
