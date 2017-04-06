package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Size;

import java.util.ArrayList;

public class Game {
    private Map map;
    private ArrayList<VariableObject> enemies;
    private PlayerObject player;
    private Point recentUserClick;

    public Game() {
        map = new Map();
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

        player.updateRotation(recentUserClick);
        player.updateLocation();
    }

    public void draw(Canvas canvas){
//        map.draw(canvas, player);
        for (VariableObject enemy:
                enemies) {
            enemy.draw(canvas, player.getLocation());
        }
        if(player != null) {
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
