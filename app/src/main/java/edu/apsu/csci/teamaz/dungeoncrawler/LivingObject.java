package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Point;
import android.util.Size;

/**
 * This is the class for objects that can die. It needs to be expanded.
 */

public class LivingObject extends VariableObject {
    private int health;

    public LivingObject(Point location, int rotation, Size size, int step, Context context) {
        super(location, rotation, size, step, context);
    }
}
