package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

import static java.lang.Math.abs;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private static long SEED = 2873123;
    private static Random RANDOM = new Random(SEED);

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

        //@source: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        long value = Long.parseLong(input.replaceAll("[^0-9]", ""));



        SEED = value;
        RANDOM = new Random(SEED);

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        //ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        //create a 2d array to store all the position
        Position[][] coordinate = new Position[WIDTH][HEIGHT];
        Set<Room> rooms = new HashSet<>();
        Set<Position> occupied = new HashSet<>();
        Set<Position> startingPool = new HashSet<>();

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
                coordinate[x][y] = new Position(x,y);
                if (x >= (WIDTH - 3) || y >= (HEIGHT - 3)) {
                    continue;
                } else {
                    startingPool.add(coordinate[x][y]);
                }
            }
        }

        System.out.println("Nothing world generated");

        int roomNumber = RandomUtils.uniform(RANDOM, 15, 35);

        for (int i = 0; i < roomNumber; i++) {
            makeRoom(world, coordinate, occupied, startingPool, rooms);
        }


        for (Room r: rooms) {
            r.makeBranch(coordinate, world, RANDOM);
            r.makeBranch(coordinate, world, RANDOM);
        }




        //ter.renderFrame(world);

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

    private static Position randomPoint(List<Position> space) {
        // the max starting point should not reach the edge
        Object[] spaceArray = space.toArray();
        RandomUtils.shuffle(RANDOM, spaceArray);
        Position p = (Position) spaceArray[0];
        return p;
    }



    private static Room makeRectangle(Position p, TETile[][] world, Position[][] coordinate, boolean isHallWay) {
        System.out.println("Position p.x: " + p.x);
        System.out.println("Position p.y: " + p.y);

        int heightLimit = HEIGHT - p.y;
        int widthLimit = WIDTH - p.x;
        int width;
        int height;


        if (isHallWay) {
            //50% probability to generate a horizontal hallway or vertical hallway
            if (RandomUtils.bernoulli(RANDOM, 0.5)) {
                width = 3;
                if (heightLimit == 3) {
                    height = 3;
                } else {
                    height = RandomUtils.uniform(RANDOM, 3, heightLimit);
                }
            } else {
                height = 3;
                if (widthLimit == 3) {
                    width = 3;
                } else {
                    width = RandomUtils.uniform(RANDOM, 3, widthLimit);
                }
            }
        } else {
            if (heightLimit > 10) {
                heightLimit = 10;
            }
            height = 3;
            if (heightLimit != 3) {
                height = RandomUtils.uniform(RANDOM, 3, heightLimit);
            }
            System.out.println("Initial height:" + height);

            if (widthLimit > 10) {
                widthLimit = 10;
            }
            width = 3;
            if (widthLimit != 3) {
                width = RandomUtils.uniform(RANDOM, 3, widthLimit);
            }
            System.out.println("Initial width:" + width);

        }

        System.out.println("Final height: " + height);
        System.out.println("Final width: " + width);

        //initialize new room
        Room room = new Room(p, width, height, coordinate);
        return room;
    }

    //@source https://stackoverflow.com/questions/8708542/something-like-contains-any-for-java-set
    //check if the new generated Room have any overlap with the existing room.
    public static boolean isOverlapped(Set occupied, Room newRoom) {
        return (occupied.stream().anyMatch(newRoom.entireRoom::contains));
    }

    public static Room makeRoom(TETile[][] world, Position[][] coordinate, Set occupied, Set startingPool, Set rooms) {
        //make a new room
        Position randomP = randomPoint(startingPool);
        System.out.println("Generate the starting point");
        Room r = makeRectangle(randomP, world, coordinate, false);
        System.out.println("Generate a new room");

        while (isOverlapped(occupied,r)) {
            //remake the room
            System.out.println("Overlapped. Regeneration begin.");
            randomP = randomPoint(startingPool);
            System.out.println("Generate the starting point");
            r = makeRectangle(randomP, world, coordinate, false);
            System.out.println("Generate a new room");
        }

        r.drawRoom(world);

        occupied.addAll(r.entireRoom);
        startingPool.removeAll(r.entireRoom);
        rooms.add(r);
        System.out.println("New Room Added");

        return r;
    }
}
