package edu.apsu.csci.teamaz.dungeoncrawler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.Door;
import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.DoorLink;
import edu.apsu.csci.teamaz.dungeoncrawler.worldobjects.WorldObject;

/**
 * A room in the map.
 */

public class Room {
    public static final int SIZE = 500;
    public static final Size TILE_SIZE = new Size(SIZE, SIZE);

    private WorldObject[][] map;
    private Door[] doors;
    private Context context;

    /* Constructor(s) */
    /***********************/
    public Room(Context context, String mapName) {
        this.context = context;
        loadMap(mapName + ".csv");
        loadDoors(mapName + ".xml");
    }


    /* Method(s) */
    /***********************/
    /* Draw tells each room object to draw on the given canvas. */
    public void draw(Canvas canvas, Point playerLocation, int roomNumber) {
        if (map != null) {
            for(WorldObject[] col  : map){
                for(WorldObject item: col){
                    if (item != null) {
                        item.draw(canvas, playerLocation);
                    }
                }
            }

        }
        if(doors != null){
            for (Door door: doors
                    ) {
                door.draw(canvas, playerLocation);
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
                return map[y][x].isPassable();
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

    /*Checks if the given point is within the bounds of a door object*/
    public Door checkDoors(Point targetPoint) {
        if (doors != null) {
            for (Door door : doors
                    ) {
                /* isInObject needs to be adjusted for door object.*/
                if (door.isInObject(targetPoint)) {
                    return door;
                }
            }
        }
        return null;
    }

    /*Return the unquie id associated with the door object*/
    public Door getDoor(int doorID){
        return doors[doorID];
    }

    /* Returns the center of the room. */
    public Point getCenter() {
        if (map != null) {
            return new Point(SIZE * map[0].length / 2, SIZE * map.length / 2);
        }
        return new Point(0, 0);
    }

    /* Creates a room based on a given file, which has numbers that corresponds to a specific image
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
            Toast.makeText(context, "Unable to load room", Toast.LENGTH_SHORT).show();
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

            /*These loops go through the 2d array of map and initializes
             the map through loadRoomHelper*/
            for (int col = 0; col < map.length; col++) {
                point.y = col * TILE_SIZE.getWidth();
                for (int row = 0; row < map[col].length; row++) {
                    point.x = row * TILE_SIZE.getWidth();
                    map[col][row] = loadRoomHelper(scanner.nextInt(), point);
                }
            }
            scanner.close();
        }
    }

    /*
        This method is used to read the xml associated with the room file.
    */
    private void loadDoors(String filename){
        /*Create the variables to hold the data from the xml file*/
        int doorId, targetRoomId, roatation, targetDoorId;
        double drawableX, drawableY, teleportX, teleportY;

        try {
            /*Set up the parser*/
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(context.getAssets().open(filename));


            NodeList doorNode = doc.getElementsByTagName("door");//Get all door tags under the root tag objects
            doors = new Door[doorNode.getLength()];//Initialize the length of the door array

            /*
                Extract all data from one door tag and save the door data to a
                position in th array doors.
            */
            for(int i = 0; i < doorNode.getLength(); i++){
                Node node = doorNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){

                    Element element = (Element) node;
                    /*Pull current room id*/
                    doorId =  Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());

                    /*Get all tags under the tag targetRoom to be extracted*/
                    NodeList targetRoom = element.getElementsByTagName("targetRoom");
                    node = targetRoom.item(0);
                    element = (Element) node;
                    targetRoomId = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    targetDoorId = Integer.parseInt(element.getElementsByTagName("doorId").item(0).getTextContent());

                    node = doorNode.item(i);
                    element = (Element) node;
                    /*Get all tags under the tag room to be extracted*/
                    NodeList room = element.getElementsByTagName("room");
                    node = room.item(0);
                    element = (Element) node;
                    /*Log.i("Room", filename);*/
                    drawableX = Double.parseDouble(element.getElementsByTagName("drawablex").item(0).getTextContent());
                    drawableY = Double.parseDouble(element.getElementsByTagName("drawabley").item(0).getTextContent());
                    teleportX = Double.parseDouble(element.getElementsByTagName("teleportx").item(0).getTextContent());
                    teleportY = Double.parseDouble(element.getElementsByTagName("teleporty").item(0).getTextContent());
                    roatation = Integer.parseInt(element.getElementsByTagName("rotation").item(0).getTextContent());

                    /*Save the points from the xml into point objects to be used for the door class*/
                    Point roomPoint = new Point((int)(drawableX * SIZE), (int)(drawableY * SIZE));
                    Point targetRoomPoint = new Point((int)(teleportX * SIZE), (int)(teleportY * SIZE));

                    doors[i] = new Door(new Point(roomPoint.x - SIZE/4, roomPoint.y - SIZE/4),roatation,new Size(SIZE/2, SIZE/2),context,false);
                    doors[i].setTeleportLocation(targetRoomPoint);
                    doors[i].setTeleportRotation(roatation);
                    doors[i].setLinkedRoom(targetRoomId);
                    doors[i].setLinkedID(targetDoorId);
                    doors[i].setDrawableByID(R.drawable.teleporter);
                    doors[i].setId(doorId);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /* Assigns each room tile to the proper drawable and rotation */
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

        /*Creates the world object from the information from the above switch statement*/
        worldObject = new WorldObject(point, rotation, new Size(SIZE,SIZE), context, passable);
        if (drawableId != 0) {
            worldObject.setDrawableByID(drawableId);
        }
        return worldObject;
    }

    /*
     * Legacy code from testing prior to loadMap implementation.
    //Creates a one room room for testing purposes.
    public void makeTestMap() {
        room = new WorldObject[4][4];

        //Corners
        room[0][0] = new WorldObject(new Point(0, 0), 0, TILE_SIZE, context, false);
        room[0][3] = new WorldObject(new Point(0, 3 * TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        room[3][3] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        room[3][0] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 0), 270, TILE_SIZE, context, false);


        room[0][0].setDrawableByID(R.drawable.wall_corner);
        room[0][3].setDrawableByID(R.drawable.wall_corner);
        room[3][3].setDrawableByID(R.drawable.wall_corner);
        room[3][0].setDrawableByID(R.drawable.wall_corner);

        //Top Wall
        room[1][0] = new WorldObject(new Point(TILE_SIZE.getWidth(), 0), 0, TILE_SIZE, context, false);
        room[2][0] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 0), 0, TILE_SIZE, context, false);
        room[1][0].setDrawableByID(R.drawable.wall);
        room[2][0].setDrawableByID(R.drawable.wall);

        //Left Wall
        room[0][1] = new WorldObject(new Point(0, TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        room[0][2] = new WorldObject(new Point(0, 2 * TILE_SIZE.getHeight()), 90, TILE_SIZE, context, false);
        room[0][1].setDrawableByID(R.drawable.wall);
        room[0][2].setDrawableByID(R.drawable.wall);

        //Bottom Wall
        room[1][3] = new WorldObject(new Point(TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        room[2][3] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 3 * TILE_SIZE.getHeight()), 180, TILE_SIZE, context, false);
        room[1][3].setDrawableByID(R.drawable.wall);
        room[2][3].setDrawableByID(R.drawable.wall);

        //Right Wall
        room[3][1] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), TILE_SIZE.getHeight()), 270, TILE_SIZE, context, false);
        room[3][2] = new WorldObject(new Point(3 * TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 270, TILE_SIZE, context, false);
        room[3][1].setDrawableByID(R.drawable.wall);
        room[3][2].setDrawableByID(R.drawable.wall);

        //Center
        room[1][1] = new WorldObject(new Point(TILE_SIZE.getWidth(), TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        room[1][2] = new WorldObject(new Point(TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        room[2][1] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);
        room[2][2] = new WorldObject(new Point(2 * TILE_SIZE.getWidth(), 2 * TILE_SIZE.getHeight()), 0, TILE_SIZE, context, true);

        room[1][1].setDrawableByID(R.drawable.floor);
        room[1][2].setDrawableByID(R.drawable.floor);
        room[2][1].setDrawableByID(R.drawable.floor);
        room[2][2].setDrawableByID(R.drawable.floor);
    }
    */

}
