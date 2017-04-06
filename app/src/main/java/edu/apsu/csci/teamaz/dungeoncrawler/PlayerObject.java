package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Point;
import android.util.Size;

/**
 * Created by nonam on 4/6/2017.
 */

public class PlayerObject extends VariableObject {
    public PlayerObject(Point location, int rotation, Size size, int step, Context context) {
        super(location, rotation, size, step, context);
        setDrawable(context.getDrawable(R.drawable.character));
    }


}
