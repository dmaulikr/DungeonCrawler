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

        this.player.setMapLocation(map.getCenter());
    }

    public void updateWorldObjects(){
        Point testPoint;
        for (GenericEntity enemy:
             enemies) {
            testPoint = enemy.calculateNextLocation(0,null);
            if(map.checkCollision(testPoint)){
                enemy.setMapLocation(testPoint);
            }
        }

//        Log.i("Player Click Debug", recentUserClick.toString());
        player.updateRotation(recentUserClick);
        if(isPlayerMoving) {

            if(playerSteps > 0 && playerSteps < 1){
                testPoint = player.calculateNextLocation(player.getSize().getWidth()/4, playerSteps);
            } else {
                testPoint = player.calculateNextLocation(player.getSize().getWidth()/4);
            }

            Log.i("Player TestPoint", testPoint.toString());

            if (map.checkCollision(testPoint) && playerSteps > 0) {
                player.setMapLocation(player.calculateNextLocation(0));
                Log.i("Player Steps", "" + playerSteps);
                playerSteps--;
            } else {
                isPlayerMoving = false;
                playerSteps = 0;
            }
        }
    }

    public void draw(Canvas canvas){
        canvas.translate(player.getRenderLocation().x, player.getRenderLocation().y);
        map.draw(canvas, player.getMapLocation_dp());

        for (GenericEntity enemy:
                enemies) {
            enemy.draw(canvas, player.getMapLocation_dp());
        }
        // Log.i("=================", player.toString());
        if(player != null) {
            //Log.i("=================", "In game ondraw");
            player.draw(canvas, null);
        }

    }

    //methods

    //Distance Formula
    public double getNumSteps(Point p1, Point p2, int stepSize){
        double distance = Math.abs(Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y),2)));
        Log.i("Distance between p1/p2", "" + distance);
        return  Math.ceil(distance / (stepSize / 2));
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

    public void movePlayer(Point userTargetPoint) {
        this.playerSteps = getNumSteps(player.getRenderLocation(), userTargetPoint, player.getStep());
    }


    //Fields
    private Map map;
    private ArrayList<GenericEntity> enemies;
    private PlayerEntity player;
    private Point recentUserClick;
    private double playerSteps;
    private boolean isPlayerMoving;

}
