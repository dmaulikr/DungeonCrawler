package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;

import java.util.ArrayList;

public class Game {
    private Map map;
    private ArrayList<VariableObject> enemies;
    private PlayerObject player;
    private Point recentUserClick;

    public Game(Size size, Context context) {
        map = new Map(size, context);
        Log.i("=============", "game size: " + size.getHeight() + " " + size.getWidth());
        enemies = new ArrayList<>();
        this.recentUserClick = new Point(0,0);
    }

    public void setPlayer(PlayerObject player){
        this.player = player;
    }

    public void updateWorldObjects(){
        for (VariableObject enemy:
             enemies) {
            enemy.updateLocation();
        }

        //Log.i("Player Click Debug", recentUserClick.toString());
        player.updateRotation(recentUserClick);
        player.updateLocation();
    }

    public void draw(Canvas canvas){
        map.draw(canvas, player);

        for (VariableObject enemy:
                enemies) {
            enemy.draw(canvas, player.getLocation());
        }
       // Log.i("=================", player.toString());
        if(player != null) {
            Log.i("=================", "In game ondraw");
            player.draw(canvas, null);
        }

    }

    public Point getRecentUserClick() {
        return recentUserClick;
    }

    public void setRecentUserClick(Point recentUserClick) {
        this.recentUserClick = recentUserClick;
    }

}
