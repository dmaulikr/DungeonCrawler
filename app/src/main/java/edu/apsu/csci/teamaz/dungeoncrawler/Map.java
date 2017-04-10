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
        Log.i("=============", "map constructor");
        Drawable floor = ContextCompat.getDrawable(context, R.drawable.floor);

        this.size = size;
        map = new VariableObject[100][100];
        Log.i("=============", "size: " + size.getWidth() + " " + size.getHeight());

        Point point = new Point(0,0);
        for(int i = 0; i < map.length; i++){
            Log.i("=============", "i:" + i);
            for (int j = 0; j < map[i].length; j++){
                Log.i("=============", "j:" + j);
                //map[i][j] = new VariableObject(point,0,new Size(100,100), 0,context);
                if(map[i][j] != null) {
                    Log.i("=============", "map ");
                    if((i == 0 && j == 0) || (i == 0 && j == size.getWidth()-1) ||
                            (j == 0 && i == size.getHeight()-1) ||
                            (j == size.getWidth()-1 && size.getHeight()-1 == i)){

                        map[i][j].setDrawable(R.drawable.wall_corner);
                    }else if(i == 0|| i == size.getWidth() || j == 0 || j == size.getWidth()){
                        map[i][j].setDrawable(R.drawable.wall);
                    }else {
                        map[i][j].setDrawable(R.drawable.floor);
                    }
                }else{
                    Log.i("=============", "map not intialized");
                }
                point.x += 100;
            }
            point.y += 100;
        }
    }

    public void draw(Canvas canvas, VariableObject player){
        for(int i = 0; i < map.length ; i++){
            for (int j = 0; j < map[i].length ; j++){
                if(map[i][j] != null) {
                    map[i][j].draw(canvas, player.getLocation());
                }
            }

        }
    }


}
