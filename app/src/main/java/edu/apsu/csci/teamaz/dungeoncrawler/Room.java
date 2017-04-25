package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.WorldObject;

public class Room {
    public static final Size TILE_SIZE = new Size(900, 900);
    public static final int SIZE = 900;

    private WorldObject[][] map;
    private Context context;

    /* Constructor(s) */
    /***********************/
    public Room(Context context, String mapName) {
        this.context = context;
        loadMap(mapName);
    }

    /* Method(s) */
    /***********************/
    /* Draw tells each map object to draw on the given canvas. */
    public void draw(Canvas canvas, Point playerLocation) {
        if (map != null) {
            for(WorldObject[] col  : map){
                for(WorldObject item: col){
                    if (item != null) {
                        item.draw(canvas, playerLocation);
                    }
                }
            }
        }
    }

    /* Checks if the given point is within the bounds of the object.
     *  This may need to be moved to worldobject.java
     */
    public boolean checkCollision(Point targetPoint) {
        int x, y;
        x = targetPoint.x / SIZE;
        y = targetPoint.y / SIZE;
        //Log.i("=============", "Target Point:" + targetPoint.x + " " + targetPoint.y);
        try {
            if (map != null) {
                //Log.i("=============", "Tested cell:" + x + " " + y);
                return map[x][y].isPassable();
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

    /* Returns the center of the map. */
    public Point getCenter() {
        if (map != null) {
            return new Point(SIZE * map.length / 2, SIZE * map[0].length / 2);
        }
        return new Point(0, 0);
    }

    /* Creates a map based on a given file, which has numbers that corresponds to a specific image
     * and rotation,
     */
    private void loadMap(String filename) {
        int height, width;
        Point point = new Point(0, 0);

        Scanner scanner;
        try {
            /* Opens the csv file from the assets folder. */
            scanner = new Scanner(context.getAssets().open(filename)).useDelimiter(",|\r?\n");

        } catch (FileNotFoundException e) {
            Log.i("============", "Unable to load " + filename);
            Toast.makeText(context, "Unable to load map", Toast.LENGTH_SHORT).show();
            scanner = null;
        } catch (IOException e) {
            e.printStackTrace();
            scanner = null;
        }

        /* Reads the csv file line by line.*/
        if (scanner != null) {
            height = scanner.nextInt();
            width = scanner.nextInt();

            map = new WorldObject[width][height];

            for (int col = 0; col < map.length; col++) {
                point.x = col * TILE_SIZE.getWidth();
                for (int row = 0; row < map[col].length; row++) {
                    point.y = row * TILE_SIZE.getWidth();
                    map[col][row] = loadRoomHelper(scanner.nextInt(), point);
                }
            }
            scanner.close();
        }
    }

    /* Assigns each map tile to the proper drawable and rotation */
    private WorldObject loadRoomHelper(int objectNumber, Point point) {
        int drawableId = 0;
        int rotation = 0;
        boolean passable = false;
        WorldObject worldObject;

        /*
            1 = floor
            2-5 = wall
            6-9 = door
            10-13 = wall_corner
         */
        if (objectNumber == 1) {
            drawableId = R.drawable.floor;
            passable = true;
        } else if (objectNumber >= 2 && objectNumber <= 5) {
            drawableId = R.drawable.wall;
            rotation = ((objectNumber + 2) % 4) * 90;
        } else if (objectNumber >= 10 && objectNumber <= 13) {
            drawableId = R.drawable.wall_corner;
            rotation = ((objectNumber - 6) % 4) * 90;
        }

        worldObject = new WorldObject(point, rotation, new Size(SIZE,SIZE), context, passable);
        if (drawableId != 0) {
            worldObject.setDrawableByID(drawableId);
        }
        return worldObject;
    }

    /*
     * Legacy code from testing prior to loadMap implementation.
    //Creates a one room map for testing purposes.
    public void makeTestMap() {
        map = new WorldObject[4][4];

        //Corners
        map[0][0] = new WorldObject(new Point(0, 0), 0, TILE_SIZE, context, false);
        map[0][3] = new WorldObject(new Point(0, 3 * TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        map[3][3] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        map[3][0] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 0), 270, TILE_SIZE, context, false);


        map[0][0].setDrawableByID(R.drawable.wall_corner);
        map[0][3].setDrawableByID(R.drawable.wall_corner);
        map[3][3].setDrawableByID(R.drawable.wall_corner);
        map[3][0].setDrawableByID(R.drawable.wall_corner);

        //Top Wall
        map[1][0] = new WorldObject(new Point(TILE_SIZE.getWidth(), 0), 0, TILE_SIZE, context, false);
        map[2][0] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 0), 0, TILE_SIZE, context, false);
        map[1][0].setDrawableByID(R.drawable.wall);
        map[2][0].setDrawableByID(R.drawable.wall);

        //Left Wall
        map[0][1] = new WorldObject(new Point(0, TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        map[0][2] = new WorldObject(new Point(0, 2 * TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        map[0][1].setDrawableByID(R.drawable.wall);
        map[0][2].setDrawableByID(R.drawable.wall);

        //Bottom Wall
        map[1][3] = new WorldObject(new Point(TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        map[2][3] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        map[1][3].setDrawableByID(R.drawable.wall);
        map[2][3].setDrawableByID(R.drawable.wall);

        //Right Wall
        map[3][1] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), TILE_SIZE.getHeight()), 270, TILE_SIZE, context, false);
        map[3][2] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 270, TILE_SIZE, context, false);
        map[3][1].setDrawableByID(R.drawable.wall);
        map[3][2].setDrawableByID(R.drawable.wall);

        //Center
        map[1][1] = new WorldObject(new Point(TILE_SIZE.getWidth(), TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        map[1][2] = new WorldObject(new Point(TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        map[2][1] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        map[2][2] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);

        map[1][1].setDrawableByID(R.drawable.floor);
        map[1][2].setDrawableByID(R.drawable.floor);
        map[2][1].setDrawableByID(R.drawable.floor);
        map[2][2].setDrawableByID(R.drawable.floor);
    }
    */

}
