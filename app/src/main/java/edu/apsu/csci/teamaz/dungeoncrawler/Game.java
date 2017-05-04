package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;

import java.util.ArrayList;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.Door;
import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.GenericEntity;
import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.PlayerEntity;

public class Game {
    final int FADECOUNT_MAX = 20;

    private Room room;
    private PlayerEntity player;
    private Map map;
    private double zoom;
    private int fadeCount;
    private Paint fadePaint;

    /* Constructor(s) */
    /***********************/
    public Game(Size size, Context context, PlayerEntity player) {
        this.map = new Map(context);
        this.room = map.getRoom();
        this.player = player;
        fadeCount = 0;
        fadePaint = new Paint();
        fadePaint.setStyle(Paint.Style.FILL);
        fadePaint.setColor(Color.BLACK);
        /* Testing code for player location. */
        this.player.setMapLocation(room.getCenter());
        //Log.i("=============", "game size: " + size.getHeight() + " " + size.getWidth());
    }

    /* Methods */
    /***********************/
    /* UpdateWorldObjects moves all the movable entities around.*/
    public void updateWorldObjects(){
        Point testPoint;
        int targetID;

//        Log.i("Player Click Debug", recentUserClick.toString());
        if(player.isMoving()) {
            testPoint = player.calculateNextLocation(0);

            //Log.i("Player TestPoint", testPoint.toString());
            Door testDoor = map.getRoom().checkDoors(testPoint);
            if(testDoor != null && testDoor.getLinkedRoom() != -1) {
                map.setCurrentRoom(testDoor.getLinkedRoom());
                Log.i("Next Room", "" + testDoor.getLinkedRoom());
                targetID = testDoor.getLinkedID();
                testDoor = map.getDoor(targetID);
                player.setMapLocation(testDoor.getTeleportLocation());
                player.setMoving(false);
                player.setRotation(testDoor.getRotation());
                fadeCount = FADECOUNT_MAX;
            }

            if (map.getRoom().checkCollision(testPoint) && player.getNumberSteps() > 0) {
//                if (true) {
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

        /* This section tells each part to draw from room to obstacles and finally the player. */
        map.draw(canvas, player.getMapLocation_dp());

        // Log.i("=================", player.toString());
        if(player != null) {
            player.draw(canvas, null);
            //Log.i("=================", "In game ondraw");
        }

        /* Fades in when room is changed */
        if(fadeCount > 0){
            int step = 0xff / FADECOUNT_MAX;
            fadePaint.setAlpha(step * fadeCount);
            canvas.drawPaint(fadePaint);
            fadeCount--;
        }
    }

    /* Moves the player from their current location to 'targetPoint'. */
    public void movePlayerTo(Point targetPoint){
        player.moveTo(targetPoint);
        //Log.i("Player Debug", targetPoint.toString());
    }

    /* Getters and Setters */
    /***********************/
    /* User Character for the game. */
    public void setPlayer(PlayerEntity player){
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    /* Code representation of the room. */
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


}
