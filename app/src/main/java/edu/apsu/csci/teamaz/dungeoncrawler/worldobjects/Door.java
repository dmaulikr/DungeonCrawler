package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Point;
import android.util.Size;

/**
 * Door for moving between rooms
 */

public class Door extends WorldObject {
    protected DoorLink link;
    /* Constructor(s) */
    /***********************/
    public Door(Point location, int rotation, Size size, Context context, boolean isPassable, DoorLink link) {
        super(location, rotation, size, context, isPassable);
        this.link = link;
    }

    public boolean isInObject(Point targetPoint) {
     if(super.isInObject(targetPoint)){
//         if(mapLocation == link.location1){
//             setMapLocation(link.location2);
//         }
//         else {
//             setMapLocation(link.location1);
//         }
         return true;
     }
         return false;
    }

    /* Getters and Setters */
    /***********************/
    /* Link for the door */
    public DoorLink getLink() {
        return link;
    }

    public void setLink(DoorLink link) {
        this.link = link;
    }
}
