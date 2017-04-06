package edu.apsu.csci.teamaz.dungeoncrawler;

import android.graphics.Canvas;
import android.util.Size;

public class Map {
    private VariableObject[][] map;
    private Size size;

    public Map(){
    }

    public void draw(Canvas canvas, VariableObject player){
        for(int i = 0; i < size.getHeight() - 1; i++){
            for (int j = 0; i < size.getWidth() - 1; j++){
                if(map[i][j] != null) {
                    map[i][j].draw(canvas, player.getLocation());
                }
            }

        }
    }


}
