package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;

import java.util.Vector;

/**
 * Created by Amy on 4/25/2017.
 */

public class Map {
    Vector<Room> room = new Vector<>();
    Context context;


    Map(Context context){
        this.context = context;
        room.add(new Room(context, "testmap.csv"));
    }
    public void addRoom(String filename){
        room.add(new Room(context, filename));
    }
    public Room getRoom(int position){
        if(position >= 0 && position < room.size()) {
            return room.get(position);
        }
        return null;
    }
}
