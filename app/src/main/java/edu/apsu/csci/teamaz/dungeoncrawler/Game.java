package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.util.Size;

import java.util.ArrayList;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.GenericEntity;
import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.PlayerEntity;

public class Game {
    public Game(Size size, Context context, PlayerEntity player) {
        map = new Map(new Size(4,4), context, "testmap.csv");
        //Log.i("=============", "game size: " + size.getHeight() + " " + size.getWidth());
        enemies = new ArrayList<>();
        this.player = player;

//        map.makeTestMap();

        this.player.setMapLocation(map.getCenter());
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
        if(player.isMoving()) {
            testPoint = player.calculateNextLocation(0);

            //Log.i("Player TestPoint", testPoint.toString());

            if (map.checkCollision(testPoint) && player.getNumberSteps() > 0) {
                player.setMapLocation(player.calculateNextLocation(0));
                //Log.i("Player Steps", "" + playerSteps);
                player.setNumberSteps(player.getNumberSteps()- 1);
            } else {
                player.setMoving(false);
                player.setNumberSteps(0);
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
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;


        double distance = Math.abs(Math.sqrt(x*x + y*y)) / player.getScale();
        Log.i("Distance between p1/p2", "" + distance);
        return  distance / stepSize;
    }

    public void movePlayerTo(Point targetPoint){
        player.updateRotation(targetPoint);
        player.setMoving(true);

        Log.i("Player Debug", targetPoint.toString());
        player.setNumberSteps(getNumSteps(player.getRenderLocation(), targetPoint, player.getStep()));
    }

    //setters
    public void setPlayer(PlayerEntity player){
        this.player = player;
    }

    //Fields
    private Map map;
    private ArrayList<GenericEntity> enemies;
    private PlayerEntity player;
}
