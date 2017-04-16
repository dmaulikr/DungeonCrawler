package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Size;

/**
 * Created by nonam on 4/6/2017.
 */

public class PlayerObject extends LivingObject {
    public PlayerObject(Point location, int rotation, Size size, int step, Context context) {
        super(new Point(100,100), rotation, size, step, context);
        setRenderLocation(location);
        Drawable playerDrawable = ContextCompat.getDrawable(context, R.drawable.character);
        setDrawable(R.drawable.character);
    }

    @Override
    public void updateLocation() {
        Point p = getMapLocation();
        p.x = p.x - (int) (-getStep() *  Math.sin(Math.toRadians(getRotation())));
        p.y = p.y + (int) (getStep() *  Math.cos(Math.toRadians(-getRotation())));

        Log.i("=================", "Player Rotation " + getRotation());
        Log.i("=================", "New Player Location " + p.toString());
        setMapLocation(p);
    }

    @Override
    public String toString() {
        return "PlayerObject Point: (" + getRenderLocation().x + "," + getRenderLocation().y + ")";
    }
}
