package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Size;

import edu.apsu.csci.teamaz.dungeoncrawler.R;

/**
 * The PlayerEntity class is a subclass of the GenericEntity class. It serves primarily as a means of
 * keeping track and drawing the player on screen.
 *
 * Render location should always bee the center of the screen.
 */

public class PlayerEntity extends GenericEntity {
    public PlayerEntity(Point location, int rotation, Size size, int step, Context context) {
        super(new Point(100,100), rotation, size, step, context);
        setRenderLocation(location);
        setDrawable(R.drawable.character);
        renderOffset = new Point();
    }

    @Override
    public void updateRotation(Point targetPoint){
        //this is needs to be researched more
        setRotation((int) Math.toDegrees(Math.atan2(targetPoint.x - renderLocation.x, targetPoint.y - renderLocation.y)));
    }

    @Override
    public void draw(Canvas canvas, Point offset){
        if(drawable != null) {
            drawable.setBounds(0 - size.getWidth() / 2, 0 - size.getHeight() / 2,
                    size.getWidth() / 2, size.getHeight() / 2);
            canvas.save(Canvas.MATRIX_SAVE_FLAG);
            canvas.translate(renderLocation.x, renderLocation.y);

            canvas.rotate(-rotation);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    @Override
    public String toString() {
        return "PlayerEntity Point: (" + getRenderLocation().x + "," + getRenderLocation().y + ")";
    }

    public Point getRenderOffset() {
        renderOffset.x = renderLocation.y - mapLocation.x;
        renderOffset.y = renderLocation.y - mapLocation.y;
        return renderOffset;
    }

    public Point getRenderLocation() {
        return renderLocation;
    }

    public void setRenderLocation(Point renderLocation) {
        this.renderLocation = renderLocation;
    }

    protected Point renderLocation;
    protected Point renderOffset;
}
