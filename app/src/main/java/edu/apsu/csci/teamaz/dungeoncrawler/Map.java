package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.GenericEntity;
import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.WorldObject;

public class Map {
    private Size TILE_SIZE = new Size(900, 900);
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
                //Log.i("=============", "Tested cell:" + x + " " + y);
                return map[x][y].isPassable();
            }
        }catch (IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    public Point getCenter(){
        if(map != null) {
            return new Point(TILE_SIZE.getWidth() * map.length / 2, TILE_SIZE.getHeight() * map[0].length / 2);
        }
        return new Point(0,0);
    }


    //creates a map based on a given file, which has
    //numbers that corresponds to a specific image and rotation
    private void loadMap(String filename){
        int height, width;
        Point point = new Point(0,0);

        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            Log.i("============", "Unable to load " + filename);
            Toast.makeText(context, "Unable to load map", Toast.LENGTH_SHORT).show();
            scanner = null;
        }
        if(scanner != null) {
            width = scanner.nextInt();
            height = scanner.nextInt();

            map = new WorldObject[width][height];

            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[row].length; col++) {
                    map[row][col] = loadMapHelper(scanner.nextInt(), point);
                    point.x += 300;
                }
                point.y += 300;
            }
            scanner.close();
        }
    }
    private WorldObject loadMapHelper(int objectNumber, Point point){
        int drawableId = 0;
        int rotation = 0;
        boolean passable = false;

        /*
            1 = floor
            2-5 = wall
            6-9 = door
            10-13 = wall_corner
         */
        switch (objectNumber){
            case 1:
                drawableId = R.drawable.floor;
                passable = true;
                break;
            case 2:
                drawableId = R.drawable.wall;
                rotation = 180;
                break;
            case 3:
                drawableId = R.drawable.wall;
                rotation = 270;
                break;
            case 4:
                drawableId = R.drawable.wall;
                rotation = 0;
                break;
            case 5:
                drawableId = R.drawable.wall;
                rotation = 90;
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                drawableId = R.drawable.wall_corner;
                rotation = 90;
                break;
            case 11:
                drawableId = R.drawable.wall_corner;
                rotation = 0;
                break;
            case 12:
                drawableId = R.drawable.wall_corner;
                rotation = 270;
                break;
            case 13:
                drawableId = R.drawable.wall_corner;
                rotation = 180;
                break;
        }
        WorldObject worldObject = new WorldObject(point,rotation,TILE_SIZE, context, passable);
        if(drawableId != 0) {
            worldObject.setDrawable(drawableId);
        }
        return  worldObject;
    }

    //Creates a one room map for testing purposes.
    public void makeTestMap() {
        map = new WorldObject[4][4];

        //Corners
        map[0][0] = new WorldObject(new Point(0, 0), 0, TILE_SIZE, context, false);
        map[0][3] = new WorldObject(new Point(0, 3 * TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        map[3][3] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        map[3][0] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 0), 270, TILE_SIZE, context, false);


        map[0][0].setDrawable(R.drawable.wall_corner);
        map[0][3].setDrawable(R.drawable.wall_corner);
        map[3][3].setDrawable(R.drawable.wall_corner);
        map[3][0].setDrawable(R.drawable.wall_corner);

        //Top Wall
        map[1][0] = new WorldObject(new Point(TILE_SIZE.getWidth(), 0), 0, TILE_SIZE, context, false);
        map[2][0] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 0), 0, TILE_SIZE, context, false);
        map[1][0].setDrawable(R.drawable.wall);
        map[2][0].setDrawable(R.drawable.wall);

        //Left Wall
        map[0][1] = new WorldObject(new Point(0, 1 * TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        map[0][2] = new WorldObject(new Point(0, 2 * TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        map[0][1].setDrawable(R.drawable.wall);
        map[0][2].setDrawable(R.drawable.wall);

        //Bottom Wall
        map[1][3] = new WorldObject(new Point(TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        map[2][3] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        map[1][3].setDrawable(R.drawable.wall);
        map[2][3].setDrawable(R.drawable.wall);

        //Right Wall
        map[3][1] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), TILE_SIZE.getHeight()), 270, TILE_SIZE, context, false);
        map[3][2] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 270, TILE_SIZE, context, false);
        map[3][1].setDrawable(R.drawable.wall);
        map[3][2].setDrawable(R.drawable.wall);

        //Center
        map[1][1] = new WorldObject(new Point(TILE_SIZE.getWidth(), TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        map[1][2] = new WorldObject(new Point(TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        map[2][1] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(),TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        map[2][2] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);

        map[1][1].setDrawable(R.drawable.floor);
        map[1][2].setDrawable(R.drawable.floor);
        map[2][1].setDrawable(R.drawable.floor);
        map[2][2].setDrawable(R.drawable.floor);
    }


}
