package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;

import java.util.Vector;

/**
 * Created by Amy on 4/25/2017.
 */

public class Map2 {
    Vector<Map> map = new Vector<>();
    Context context;


    Map2(Context context){
        this.context = context;
        map.add(new Map(context, "testmap.csv"));
    }
    public void addRoom(String filename){
        map.add(new Map(context, filename));
    }
    public Map getRoom(int position){
        if(position >= 0 && position < map.size()) {
            return map.get(position);
        }
        return null;
    }
}
