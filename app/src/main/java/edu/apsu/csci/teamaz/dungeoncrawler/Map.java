package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Size;

public class Map {
    private VariableObject[][] map;
    private Size size;

    public Map(Size size, Context context){
        Drawable floor = ContextCompat.getDrawable(context, R.drawable.floor);

        this.size = size;
        initalizeMap(size.getHeight()/300,size.getWidth()/300);
    }

    public void draw(Canvas canvas, VariableObject player){
        for(int i = 0; i < map.length ; i++){
            for (int j = 0; j < map[i].length ; j++){
                if(map[i][j] != null) {
                    map[i][j].draw(canvas, player.getRenderLocation());
                }
            }

        }
    }

    //creates a map based on the given dimensions
    //will put wall and wall_corners on the outer most edge
    private void initalizeMap(int height, int width){
        map = new VariableObject[height][width];
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    Log.i("=============", "map ");
                    if ((i == 0 && j == 0) || (i == 0 && j == map[i].length - 1) ||
                            (j == 0 && i == map.length - 1) ||
                            (j == map[i].length - 1 && map.length - 1 == i)) {

                        map[i][j].setDrawable(R.drawable.wall_corner);
                    } else if (i == 0 || i == map.length - 1 || j == 0 || j == map[i].length - 1) {
                        map[i][j].setDrawable(R.drawable.wall);
                    } else {
                        map[i][j].setDrawable(R.drawable.floor);
                    }
                } else {
                    Log.i("=============", "map not intialized");
                }

            }
        }
    }

}
