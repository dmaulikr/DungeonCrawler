package edu.apsu.csci.teamaz.dungeoncrawler.worldobjects;

import android.graphics.Point;

/**
 * This file is no longer used in the current version of this program
 */

public class DoorLink {
    protected int room1;
    protected Point location1;
    protected int room2;
    protected Point location2;

    /* Constructor(s) */
    /***********************/
    public DoorLink(int room1, int x1, int y1, int room2, int x2, int y2) {
        this.room1 = room1;
        this.location1 = new Point(x1,y1);
        this.room2 = room2;
        this.location2 = new Point(x2,y2);
    }

    /* Getters and Setters */
    /***********************/
    /* One room for the link. */
    public int getRoom1() {
        return room1;
    }

    /* The actual location on the abstract map. */
    public void setRoom1(int room1) {
        this.room1 = room1;
    }

    /* Spawn Location for door in room 1. */
    public Point getLocation1() {
        return location1;
    }

    public void setLocation1(Point location1) {
        this.location1 = location1;
    }

    public int getRoom2() {
        return room2;
    }

    /* Other room for the link. */
    public void setRoom2(int room2) {
        this.room2 = room2;
    }

    /* Spawn Location for door in room 1. */
    public Point getLocation2() {
        return location2;
    }

    public void setLocation2(Point location2) {
        this.location2 = location2;
    }
}
