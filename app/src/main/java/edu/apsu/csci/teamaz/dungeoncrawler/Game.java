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
    private Map map;
    private ArrayList<GenericEntity> obstacles;
    private PlayerEntity player;

    /* Constructor(s) */
    /***********************/
    public Game(Size size, Context context, PlayerEntity player) {
        this.map = new Map(new Size(4,4), context, "testmap.csv");
        this.obstacles = new ArrayList<>();
        this.player = player;

        /* Testing code for player location. */
        this.player.setMapLocation(map.getCenter());
        //Log.i("=============", "game size: " + size.getHeight() + " " + size.getWidth());
    }

    /* Methods */
    /***********************/
    /* UpdateWorldObjects moves all the movable entities around.*/
    public void updateWorldObjects(){
        Point testPoint;
        for (GenericEntity enemy:
                obstacles) {
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

    /* Passes Draw calls to respective entities. */
    public void draw(Canvas canvas){
        /* Sets the canvas 0,0 location to the players render location. */
        canvas.translate(player.getRenderLocation().x, player.getRenderLocation().y);

        /* This section tells each part to draw from map to obstacles and finally the player. */
        map.draw(canvas, player.getMapLocation_dp());

        for (GenericEntity enemy:
                obstacles) {
            enemy.draw(canvas, player.getMapLocation_dp());
        }

        // Log.i("=================", player.toString());
        if(player != null) {
            player.draw(canvas, null);
            //Log.i("=================", "In game ondraw");
        }

    }

    /* Moves the player from their current location to 'targetPoint'. */
    public void movePlayerTo(Point targetPoint){
        player.moveTo(targetPoint);
        //Log.i("Player Debug", targetPoint.toString());
    }

    /* Getters and Setters */
    /***********************/

    /* Obstacles for the map including doors. */
    public ArrayList<GenericEntity> getObstacles() {
        return obstacles;
    }

    public void setObstacles(ArrayList<GenericEntity> obstacles) {
        this.obstacles = obstacles;
    }

    /* User Character for the game. */
    public void setPlayer(PlayerEntity player){
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    /* Code representation of the map. */
    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }


}
