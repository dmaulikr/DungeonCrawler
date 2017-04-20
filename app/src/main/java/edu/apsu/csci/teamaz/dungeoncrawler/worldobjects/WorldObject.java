package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Size;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import edu.apsu.csci.teamaz.dungeoncrawler.Map;

/**
 * Base class for objects that don't move or have health and the bare minimum for objects that are
 * drawn on screen.
 */

public class WorldObject {

    //Constructor
    public WorldObject(Point location, int rotation, Size size, Context context, boolean isPassable) {
        this.mapLocation = location;
        this.rotation = rotation;
        this.context = context;
        this.isPassable = isPassable;
        mapLocation_dp = new Point(location);

        //This helps the object scale.
        //set Size is used because it automatically sets size_dp
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        scale_dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1,metrics);
//        scale_dp = (float) (windowSize.x * 1.3 / Map.SIZE);
        setSize(size);
        setMapLocation(location);
    }

    //Methods
    public void draw(Canvas canvas, Point offset){
        if(offset == null){
            offset = new Point(0,0);
        }

        if(drawable != null) {
            drawable.setBounds(0 - size_dp.getWidth() / 2, 0 - size_dp.getHeight() / 2,
                    size_dp.getWidth() / 2, size_dp.getHeight() / 2);
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            canvas.translate(mapLocation_dp.x - offset.x + size_dp.getWidth()/2,
                    mapLocation_dp.y - offset.y + size_dp.getWidth()/2);

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

    public float getScale(){return scale_dp;}

    public boolean isPassable() {
        return isPassable;
    }

    public Point getMapLocation_dp(){return mapLocation_dp;}

    //Setters
    public void setMapLocation(Point location) {
        mapLocation_dp.x = (int) Math.ceil(location.x / scale_dp);
        mapLocation_dp.y = (int) Math.ceil(location.y / scale_dp);
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
        size_dp = new Size((int) Math.ceil(size.getWidth() / scale_dp) + 1, (int) Math.ceil(size.getHeight() / scale_dp ) + 1);
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
    protected float scale_dp;
    protected Size size_dp;
    protected Point mapLocation_dp;
}
