package byog.Core;

//import byog.TileEngine.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static java.lang.Math.abs;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        Position[][] coordinate = new Position[WIDTH][HEIGHT];
        Set<Room> rooms = new HashSet<>();
        Set<Position> occupied = new HashSet<>();
        Set<Position> startingPool = new HashSet<>();

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
                coordinate[x][y] = new Position(x,y);
                if (x >= (WIDTH - 2) || y >= (HEIGHT - 2)) {
                    continue;
                } else {
                    startingPool.add(coordinate[x][y]);
                }
            }
        }

        System.out.println("Nothing world generated");

        for (int i = 0; i < 30; i++) {
            makeRoom(world, coordinate, occupied, startingPool, rooms);
            if (startingPool.size() < 1000) {
                break;
            }
        }

        ter.renderFrame(world);

        //return finalWorldFrame;
        return world;
    }


    private static Position randomPoint(Set space) {
        // the max starting point should not reach the edge
        Object[] spaceArray = space.toArray();
        RandomUtils.shuffle(RANDOM, spaceArray);
        Position p = (Position) spaceArray[0];
        return p;
    }

    private static Position randomPoint(ArrayList<Position> space) {
        // the max starting point should not reach the edge
        Object[] spaceArray = space.toArray();
        RandomUtils.shuffle(RANDOM, spaceArray);
        Position p = (Position) spaceArray[0];
        return p;
    }



    private static Room makeRectangle(Position p, TETile[][] world, Position[][] coordinate) {
        //Position[] WidthLine = coordinate[p.x].clone();
        //Position horizontalP = randomPoint(p.availableHeight(WidthLine));
        //int height = abs(horizontalP.y - p.y);
        System.out.println("Position p.x: " + p.x);
        System.out.println("Position p.y: " + p.y);
        int height = RandomUtils.uniform(RANDOM, 3, 8);
        System.out.println("Initial height" + height);
        int width = RandomUtils.uniform(RANDOM, 3, 8);
        System.out.println("Initial width" + width);
        while(p.y + height > HEIGHT) {
            height = RandomUtils.uniform(RANDOM, 3, 8);
            System.out.println("Rerun height" + height);
        }
        while(p.x + width > WIDTH) {
            width = RandomUtils.uniform(RANDOM, 3, 8);
            System.out.println("Rerun width" + width);
        }
        //initialize new room
        Room room = new Room(p, width, height, coordinate);
        return room;
    }


    //@source https://stackoverflow.com/questions/8708542/something-like-contains-any-for-java-set
    //check if the new generated Room have any overlap with the existing room.
    public static boolean isOverlapped(Set occupied, Room newRoom) {
        return (occupied.stream().anyMatch(newRoom.entireRoom::contains));
    }

    public static void makeRoom(TETile[][] world, Position[][] coordinate, Set occupied, Set startingPool, Set rooms) {
        //make a new room
        Position randomP = randomPoint(startingPool);
        System.out.println("Generate the starting point");
        Room r = makeRectangle(randomP, world, coordinate);
        System.out.println("Generate a new room");

        while (isOverlapped(occupied,r)) {
            //remake the room
            System.out.println("Overlapped. Regeneration begin.");
            randomP = randomPoint(startingPool);
            System.out.println("Generate the starting point");
            r = makeRectangle(randomP, world, coordinate);
            System.out.println("Generate a new room");
        }
        //draw the perimeter
        for (Position point: r.perimeter) {
            world[point.x][point.y] = Tileset.WALL;
            point.status = 1;
        }
        for (Position point: r.inner) {
            world[point.x][point.y] = Tileset.FLOOR;
            point.status = 1;
        }
        occupied.addAll(r.entireRoom);
        startingPool.removeAll(r.entireRoom);
        rooms.add(r);
        System.out.println("New Room Added");
    }


}
