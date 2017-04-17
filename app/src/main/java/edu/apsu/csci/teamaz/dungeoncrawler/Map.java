package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.GenericEntity;
import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.WorldObject;

public class Map {
    private Size TILE_SIZE = new Size(300, 300);
    private WorldObject[][] map;
    private Context context;


    public Map(Size size, Context context) {
        this.context = context;
    }

    //Methods
    public void draw(Canvas canvas, Point playerLocation) {
        if (map != null) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != null) {
                        map[i][j].draw(canvas, playerLocation);
                    }
                }

            }
        }
    }

    public boolean checkCollision(Point targetPoint) {
        int x, y;
        x = targetPoint.x / TILE_SIZE.getWidth();
        y = targetPoint.y / TILE_SIZE.getHeight();
        //Log.i("=============", "Target Point:" + targetPoint.x + " " + targetPoint.y);
        try {
            if (map != null) {
                Log.i("=============", "Tested cell:" + x + " " + y);
                return map[x][y].isPassable();
            }
        }catch (IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }


    //creates a map based on the given dimensions
    //will put wall and wall_corners on the outer most edge
    private void initalizeMap(int height, int width) {
        map = new GenericEntity[height][width];
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] != null) {
                    Log.i("=============", "map ");
                    if ((row == 0 && col == 0) || (row == 0 && col == map[row].length - 1) ||
                            (col == 0 && row == map.length - 1) ||
                            (col == map[row].length - 1 && map.length - 1 == row)) {

                        map[row][col].setDrawable(R.drawable.wall_corner);
                    } else if (row == 0 || row == map.length - 1 || col == 0 || col == map[row].length - 1) {
                        map[row][col].setDrawable(R.drawable.wall);
                    } else {
                        map[row][col].setDrawable(R.drawable.floor);
                    }
                } else {
                    Log.i("=============", "map not intialized");
                }

            }
        }
    }

    //Creates a one room map for testing purposes.
    public void makeTestMap() {
        map = new WorldObject[4][4];

        //Corners
        map[0][0] = new WorldObject(new Point(0, 0), 0, TILE_SIZE, context, false);
        map[0][3] = new WorldObject(new Point(0, 900), 90, TILE_SIZE, context, false);
        map[3][3] = new WorldObject(new Point(900, 900), 180, TILE_SIZE, context, false);
        map[3][0] = new WorldObject(new Point(900, 0), 270, TILE_SIZE, context, false);


        map[0][0].setDrawable(R.drawable.wall_corner);
        map[0][3].setDrawable(R.drawable.wall_corner);
        map[3][3].setDrawable(R.drawable.wall_corner);
        map[3][0].setDrawable(R.drawable.wall_corner);

        //Top Wall
        map[1][0] = new WorldObject(new Point(300, 0), 0, TILE_SIZE, context, false);
        map[2][0] = new WorldObject(new Point(600, 0), 0, TILE_SIZE, context, false);
        map[1][0].setDrawable(R.drawable.wall);
        map[2][0].setDrawable(R.drawable.wall);

        //Left Wall
        map[0][1] = new WorldObject(new Point(0, 300), 90, TILE_SIZE, context, false);
        map[0][2] = new WorldObject(new Point(0, 600), 90, TILE_SIZE, context, false);
        map[0][1].setDrawable(R.drawable.wall);
        map[0][2].setDrawable(R.drawable.wall);

        //Bottom Wall
        map[1][3] = new WorldObject(new Point(300, 900), 180, TILE_SIZE, context, false);
        map[2][3] = new WorldObject(new Point(600, 900), 180, TILE_SIZE, context, false);
        map[1][3].setDrawable(R.drawable.wall);
        map[2][3].setDrawable(R.drawable.wall);

        //Right Wall
        map[3][1] = new WorldObject(new Point(900, 300), 270, TILE_SIZE, context, false);
        map[3][2] = new WorldObject(new Point(900, 600), 270, TILE_SIZE, context, false);
        map[3][1].setDrawable(R.drawable.wall);
        map[3][2].setDrawable(R.drawable.wall);

        //Center
        map[1][1] = new WorldObject(new Point(300, 300), 0, TILE_SIZE, context, true);
        map[1][2] = new WorldObject(new Point(300, 600), 0, TILE_SIZE, context, true);
        map[2][1] = new WorldObject(new Point(600, 300), 0, TILE_SIZE, context, true);
        map[2][2] = new WorldObject(new Point(600, 600), 0, TILE_SIZE, context, true);

        map[1][1].setDrawable(R.drawable.floor);
        map[1][2].setDrawable(R.drawable.floor);
        map[2][1].setDrawable(R.drawable.floor);
        map[2][2].setDrawable(R.drawable.floor);
    }


}
