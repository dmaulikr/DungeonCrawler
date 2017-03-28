package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Size;

/**
 * Created by nonam on 3/28/2017.
 */

public class StaticObject implements WorldObject {
    public Point location;
    public int rotation;
    public Size size;
    public Drawable drawable;
    public Drawable collisionDrawable;


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
}
