package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;

import java.util.ArrayList;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.GenericEntity;
import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.PlayerEntity;

public class Game {
    public Game(Size size, Context context, PlayerEntity player) {
        map = new Map(new Size(4,4), context);
        Log.i("=============", "game size: " + size.getHeight() + " " + size.getWidth());
        enemies = new ArrayList<>();
        this.recentUserClick = new Point(0,0);
        this.player = player;
        map.makeTestMap();
        isPlayerMoving = false;
    }

    public void updateWorldObjects(){
        Point testPoint;
        for (GenericEntity enemy:
             enemies) {
            testPoint = enemy.calculateNextLocation(0);
            if(map.checkCollision(testPoint)){
                enemy.setMapLocation(testPoint);
            }
        }

//        Log.i("Player Click Debug", recentUserClick.toString());
        player.updateRotation(recentUserClick);
        if(isPlayerMoving) {
            testPoint = player.calculateNextLocation(player.getSize().getWidth()/4);
            if (map.checkCollision(testPoint)) {
               player.setMapLocation(player.calculateNextLocation(0));
            }
        }
    }

    public void draw(Canvas canvas){
//        int x1,y1;
//        x1 = player.getRenderLocation().x - player.getMapLocation().x;
//        y1 = player.getRenderLocation().y - player.getMapLocation().y;
        canvas.translate(player.getRenderLocation().x, player.getRenderLocation().y);
        map.draw(canvas, player.getMapLocation());

        for (GenericEntity enemy:
                enemies) {
            enemy.draw(canvas, player.getMapLocation());
        }
        // Log.i("=================", player.toString());
        if(player != null) {
            //Log.i("=================", "In game ondraw");
            player.draw(canvas, null);
        }

    }

    //getters
    public Point getRecentUserClick() {
        return recentUserClick;
    }

    public boolean getPlayerMoving() {
        return isPlayerMoving;
    }

    //setters
    public void setRecentUserClick(Point recentUserClick) {
        this.recentUserClick = recentUserClick;
    }

    public void setPlayerMoving(boolean value){this.isPlayerMoving = value;}

    public void setPlayer(PlayerEntity player){
        this.player = player;
    }

    //Fields
    private Map map;
    private ArrayList<GenericEntity> enemies;
    private PlayerEntity player;
    private Point recentUserClick;
    private boolean isPlayerMoving;

}
