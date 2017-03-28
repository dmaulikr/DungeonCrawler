package edu.apsu.csci.teamaz.dungeoncrawler;


import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Size;

public interface WorldObject {
    //Location of the object as drawn in the worldspace.
    public Point getLocation();
    //Orientation of the object as drawn in the world space.
    public int getRotation();
    //Size of the object as drawn in the world space.
    public Size  getSize();
    //Drawable for the object.
    public Drawable getDrawable();
    //Solid color drawable for collision();
    public Drawable getCollisionDrawable();
}
