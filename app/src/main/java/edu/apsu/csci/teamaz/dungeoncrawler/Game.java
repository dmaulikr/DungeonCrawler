package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Size;

import java.util.ArrayList;

public class Game {
    private Map map;
    private ArrayList<VariableObject> enemies;
    private VariableObject player;

    public Game() {
        map = new Map();
        enemies = new ArrayList<>();
    }

    public void setPlayer(VariableObject player){
        this.player = player;
    }

    public void updateWorldObjects(){
        for (VariableObject enemy:
             enemies) {
            enemy.updateLocation();
        }

        player.updateLocation();
    }

    public void draw(Canvas canvas){
//        map.draw(canvas, player);
        for (VariableObject enemy:
                enemies) {
            enemy.draw(canvas, player.getLocation());
        }
        player.draw(canvas,null);
    }

}
