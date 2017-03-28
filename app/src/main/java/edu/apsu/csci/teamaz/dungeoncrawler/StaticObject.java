package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Size;

/*
 Base class for objects that don't move or have health. Containers will likely be a subclass.
 However, the background can be made of these.
 */

public class StaticObject implements WorldObject {

    //Constructor
    public StaticObject(Point location, int rotation, Size size) {
        this.location = location;
        this.rotation = rotation;
        this.size = size;
    }

    //Getters
    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public int getRotation() {
        return rotation;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public Drawable getDrawable() {
        return drawable;
    }

    @Override
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
