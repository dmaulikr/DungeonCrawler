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
    private StaticObject[][] map;
    private Size size;
    private Context context;

    public Map(Size size, Context context){
        Drawable floor = ContextCompat.getDrawable(context, R.drawable.floor);

        this.context = context;
        this.size = size;
        //initalizeMap(size.getHeight()/300,size.getWidth()/300);
    }

    public void draw(Canvas canvas, Point playerLocation){
        if(map != null) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != null) {
                        map[i][j].draw(canvas, playerLocation);
                    }
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

    public void makeTestMap(){
        map = new StaticObject[4][4];
        Size tileSize = new Size(300,300);
        //Corners
        map[0][0] = new StaticObject(new Point(0,0), 0, tileSize, context);
        map[0][3] = new StaticObject(new Point(0,900), 90, tileSize, context);
        map[3][0] = new StaticObject(new Point(900,0), 270, tileSize, context);
        map[3][3] = new StaticObject(new Point(900,900), 180, tileSize, context);

        map[0][0].setDrawable(R.drawable.wall_corner);
        map[0][3].setDrawable(R.drawable.wall_corner);
        map[3][0].setDrawable(R.drawable.wall_corner);
        map[3][3].setDrawable(R.drawable.wall_corner);

        //top wall
        map[0][1] = new StaticObject(new Point(0,300), 90, tileSize, context);
        map[0][2] = new StaticObject(new Point(0,600), 90, tileSize, context);
        map[0][1].setDrawable(R.drawable.wall);
        map[0][2].setDrawable(R.drawable.wall);

        //Right wall
        map[3][1] = new StaticObject(new Point(900,300), 270, tileSize, context);
        map[3][2] = new StaticObject(new Point(900,600), 270, tileSize, context);
        map[3][1].setDrawable(R.drawable.wall);
        map[3][2].setDrawable(R.drawable.wall);

        //left wall
        map[1][0] = new StaticObject(new Point(300,0), 0, tileSize, context);
        map[2][0] = new StaticObject(new Point(600,0), 0, tileSize, context);
        map[1][0].setDrawable(R.drawable.wall);
        map[2][0].setDrawable(R.drawable.wall);

        //bottom wall
        map[1][3] = new StaticObject(new Point(300,900), 180, tileSize, context);
        map[2][3] = new StaticObject(new Point(600,900), 180, tileSize, context);
        map[1][3].setDrawable(R.drawable.wall);
        map[2][3].setDrawable(R.drawable.wall);

        //Center
        map[1][1] = new StaticObject(new Point(300,300), 0, tileSize, context);
        map[1][2] = new StaticObject(new Point(300,600), 0, tileSize, context);
        map[2][1] = new StaticObject(new Point(600,300), 0, tileSize, context);
        map[2][2] = new StaticObject(new Point(600,600), 0, tileSize, context);

        map[1][1].setDrawable(R.drawable.floor);
        map[1][2].setDrawable(R.drawable.floor);
        map[2][1].setDrawable(R.drawable.floor);
        map[2][2].setDrawable(R.drawable.floor);
    }


}
