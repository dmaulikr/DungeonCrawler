package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Size;

/**
 * Created by nonam on 4/6/2017.
 */

public class PlayerObject extends VariableObject {
    public PlayerObject(Point location, int rotation, Size size, int step, Context context) {
        super(location, rotation, size, step, context);
        Drawable playerDrawable = ContextCompat.getDrawable(context, R.drawable.character);
        setDrawable(R.drawable.character);
    }

    @Override
    public String toString() {
        return "PlayerObject Point: (" + getLocation().x + "," + getLocation().y + ")";
    }
}
