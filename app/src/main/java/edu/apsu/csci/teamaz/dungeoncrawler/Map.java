package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;

import java.util.Vector;

/**
 * Created by Amy on 4/25/2017.
 */

public class Map {
    Vector<Room> map = new Vector<>();
    Context context;


    Map(Context context){
        this.context = context;
        map.add(new Room(context, "testmap.csv"));
    }
    public void addRoom(String filename){
        map.add(new Room(context, filename));
    }
    public Room getRoom(int position){
        if(position >= 0 && position < map.size()) {
            return map.get(position);
        }
        return null;
    }
}
