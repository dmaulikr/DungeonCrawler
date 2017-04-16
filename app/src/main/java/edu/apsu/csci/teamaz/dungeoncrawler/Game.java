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
    private boolean isPlayerMoving;

    public Game(Size size, Context context, PlayerObject player) {
        map = new Map(new Size(4,4), context);
        Log.i("=============", "game size: " + size.getHeight() + " " + size.getWidth());
        enemies = new ArrayList<>();
        this.recentUserClick = new Point(0,0);
        this.player = player;
        map.makeTestMap();
        isPlayerMoving = false;
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
        if(isPlayerMoving) {
            player.updateLocation();
            isPlayerMoving = false;
        }
    }

    public void draw(Canvas canvas){
        int x1,y1;
        x1 = player.getRenderLocation().x - player.getMapLocation().x;
        y1 = player.getRenderLocation().y - player.getMapLocation().y;
        map.draw(canvas, new Point(x1,y1));

        for (VariableObject enemy:
                enemies) {
            enemy.draw(canvas, player.getMapLocation());
        }
       // Log.i("=================", player.toString());
        if(player != null) {
            //Log.i("=================", "In game ondraw");
            player.draw(canvas, null);
        }

    }

    public Point getRecentUserClick() {
        return recentUserClick;
    }

    public void setRecentUserClick(Point recentUserClick) {
        this.recentUserClick = recentUserClick;
    }

    public boolean getPlayerMoving() {
        return isPlayerMoving;
    }

    public void setPlayerMoving(boolean value){this.isPlayerMoving = true;}


}
