package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Point;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.Door;

/**
 * A container for rooms.
 */

public class Map {
    protected Vector<Room> room = new Vector<>();
    protected int currentRoom;
    protected Context context;

    /* Constructor(s) */
    /***********************/
    Map(Context context){
        this.context = context;
        room.add(new Room(context, "0"));
        scanRooms("maze/");
        currentRoom = 0;
    }

    /* Method(s) */
    /***********************/

    /*This method adds rooms to the vector room by loading files from the assets folder*/
    public void scanRooms(String directory){
        AssetManager assets = context.getResources().getAssets();
        for(int i = 1; ;i++) {
            try {
            new Scanner(assets.open(directory + i + ".csv"));
                room.add(new Room(context, directory + i ));
            } catch (FileNotFoundException e) {
                return;
            } catch (IOException e) {
                return;
            }
        }
    }

    /*This allows a room to be added to the map*/
    public void addRoom(String filename){
        room.add(new Room(context, filename));
    }

    /*Returns a room object based on the currentRoom value.
     *If the currentRoom value is less than 0 or greater than the room size return null */
    public Room getRoom(){
        if(currentRoom >= 0 && currentRoom < room.size()) {
            return room.get(currentRoom);
        }
        return null;
    }

    /*Craws the current room*/
    public void draw(Canvas canvas, Point playerLocation){

        room.elementAt(currentRoom).draw(canvas, playerLocation, currentRoom);

    }

    /* Room that is currently being drawn. */
    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    /* gets door by id */
    public Door getDoor(int doorID){
        return room.elementAt(currentRoom).getDoor(doorID);
    }
}
