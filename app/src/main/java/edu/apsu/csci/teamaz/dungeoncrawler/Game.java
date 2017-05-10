package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;

import java.util.ArrayList;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.GenericWorldObject;

public class Game {
    public GenericWorldObject testObject;

    /* Constructor(s) */
    /***********************/
    public Game(Point center, Context context) {
        /* Testing code for player location. */
        testObject = new GenericWorldObject(center, 100, context, R.drawable.floor);
    }

    /* Methods */
    /***********************/
    /* UpdateWorldObjects moves all the movable entities around.*/
    public void updateWorldObjects(){
    }

    /* Passes Draw calls to respective entities. */
    public void draw(Canvas canvas){
        testObject.draw(canvas);
    }

    /* Moves the player from their current location to 'targetPoint'. */
    public void movePlayerTo(Point targetPoint){
    }

    /* Getters and Setters */
    /***********************/
    /* User Character for the game. */
//    public void setPlayer(PlayerEntity player){
//        this.player = player;
//    }
//
//    public PlayerEntity getPlayer() {
//        return player;
//    }


}
