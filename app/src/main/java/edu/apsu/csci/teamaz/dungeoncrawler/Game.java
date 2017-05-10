package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;

import java.util.ArrayList;

public class Game {
    final int FADECOUNT_MAX = 20;

    private double zoom;
    private int fadeCount;
    private Paint fadePaint;

    /* Constructor(s) */
    /***********************/
    public Game(Size size, Context context) {
        fadeCount = 0;
        fadePaint = new Paint();
        fadePaint.setStyle(Paint.Style.FILL);
        fadePaint.setColor(Color.BLACK);
        /* Testing code for player location. */
    }

    /* Methods */
    /***********************/
    /* UpdateWorldObjects moves all the movable entities around.*/
    public void updateWorldObjects(){
    }

    /* Passes Draw calls to respective entities. */
    public void draw(Canvas canvas){
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
