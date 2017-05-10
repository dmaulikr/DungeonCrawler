package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class GenericWorldObject {
    public Point location;
    public int size;
    public Rect bounds;
    public Bitmap image;
    public Paint defaultPaint;

    /* Constructor(s) */
    /******************/
    public GenericWorldObject(Point location, int size, Bitmap image) {
        this.bounds = new Rect();

        this.location = location;
        this.size = size;
        this.image = image;
        defaultPaint = new Paint();
    }

    public GenericWorldObject(Point location, int size, Context context, int drawableId) {
        this.bounds = new Rect();

        this.location = location;
        this.size = size;
        setImage(context, drawableId);
        defaultPaint = new Paint();
    }

    /* Methods */
    /***********************/
    public void draw(Canvas canvas){
        if(bounds != null) {
            canvas.drawBitmap(getImage(), bounds, bounds, defaultPaint);
        }
    }

    /* Getters and Setters */
    /***********************/
    /* Location of the object. */
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
        setBounds();
    }

    public void setLocation(int x, int y) {
        this.location.x = x;
        this.location.y = y;
        setBounds();
    }

    /* Size of the object. */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /* Bounds of the object. */
    public Rect getBounds() {
        return bounds;
    }

    private void setBounds() {
        this.bounds.left = location.x - size/2;
        this.bounds.top = location.y - size/2;
        this.bounds.right = location.x + size/2;
        this.bounds.bottom = location.y + size/2;
    }

    /* Image for the object. */
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setImage(Context context, int drawableId) {
        this.image = BitmapFactory.decodeResource(context.getResources(), drawableId);
    }
}
