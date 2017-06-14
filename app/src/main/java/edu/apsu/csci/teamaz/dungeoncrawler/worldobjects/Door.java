package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.content.Context;
import android.graphics.Point;
import android.util.Size;

/**
 * Door for moving between rooms
 */

public class Door extends WorldObject {
    protected int id;
    protected Point teleportLocation;
    protected int teleportRotation;
    protected int linkedRoom;
    protected int linkedID;
    /* Constructor(s) */
    /***********************/
    public Door(Point location, int rotation, Size size, Context context, boolean isPassable) {
        super(location, rotation, size, context, isPassable);
    }

    /*Determines if the player next move is in the object*/
    public boolean isInObject(Point targetPoint) {
     if(super.isInObject(targetPoint)){
         return true;
     }
         return false;
    }

    /* Getters and Setters */
    /***********************/
    /* Door id */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /* Teleport Map location for this door */
    public Point getTeleportLocation() {
        return teleportLocation;
    }

    public void setTeleportLocation(Point teleportLocation) {
        this.teleportLocation = teleportLocation;
    }

    /* Rotation of the player */
    public int getTeleportRotation() {
        return teleportRotation;
    }

    public void setTeleportRotation(int teleportRotation) {
        this.teleportRotation = teleportRotation;
    }

    /* Linked room */
    public int getLinkedRoom() {
        return linkedRoom;
    }

    public void setLinkedRoom(int linkedRoom) {
        this.linkedRoom = linkedRoom;
    }

    /* Id of linked door */
    public int getLinkedID() {
        return linkedID;
    }

    public void setLinkedID(int linkedID) {
        this.linkedID = linkedID;
    }
}
