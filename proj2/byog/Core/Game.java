package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private static long SEED = 2873123;
    private static Random RANDOM = new Random(SEED);
    private static TETile[][] world = new TETile[WIDTH][HEIGHT];
    private static Position[][] coordinate = new Position[WIDTH][HEIGHT];

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
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        //@source:
        // https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        long value = Long.parseLong(input.replaceAll("[^0-9]", ""));
        SEED = value;
        RANDOM = new Random(SEED);

        //TETile[][] world = new TETile[WIDTH][HEIGHT];
        //Position[][] coordinate = new Position[WIDTH][HEIGHT];

        //ter.initialize(WIDTH, HEIGHT);

        //create a 2d array to store all the position
        Set<Room> rooms = new HashSet<>();
        Set<Position> occupied = new HashSet<>();
        ArrayList<Position> startingPool = new ArrayList<>();



        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
                coordinate[x][y] = new Position(x, y);
                if (x >= (WIDTH - 3) || y >= (HEIGHT - 3)) {
                    continue;
                } else {
                    startingPool.add(coordinate[x][y]);
                }
            }
        }


        int roomNumber = RandomUtils.uniform(RANDOM, 30, 45);

        for (int i = 0; i < roomNumber; i++) {
            makeRoom(occupied, startingPool, rooms, RANDOM);
        }


        for (Room r: rooms) {
            int count = 0;
            int[] list = {0, 1, 2, 3};
            RandomUtils.shuffle(RANDOM, list);
            for (int side: list) {
                if (r.makeBranch(side, coordinate, world, RANDOM)) {
                    count++;
                }
                if (count == 2) {
                    break;
                }
            }
        }


        // draws the world to the screen
        //ter.renderFrame(world);

        //return finalWorldFrame;
        return world;
    }

    /**
    private static Position randomPoint(ArrayList space) {
        // the max starting point should not reach the edge
        int i = RandomUtils.uniform(RANDOM, 0, space.size());
        Position p = (Position) space.get(i);
        return p;
    }
     */

    private static Room makeRectangle(Position p) {
        int heightLimit = HEIGHT - p.y;
        int widthLimit = WIDTH - p.x;
        int width;
        int height;

        if (heightLimit > 10) {
            heightLimit = 10;
        }
        height = 3;
        if (heightLimit != 3) {
            height = RandomUtils.uniform(RANDOM, 3, heightLimit);
        }
        if (widthLimit > 10) {
            widthLimit = 10;
        }
        width = 3;
        if (widthLimit != 3) {
            width = RandomUtils.uniform(RANDOM, 3, widthLimit);
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

    public static Room makeRoom(Set occupied, ArrayList startingPool, Set rooms, Random r) {
        //make a new room
        Position p = Position.randomPoint(startingPool, r);
        Room room = makeRectangle(p);

        while (isOverlapped(occupied, room)) {
            //remake the room
            p = Position.randomPoint(startingPool, r);
            room = makeRectangle(p);
        }

        room.drawRoom(world);


        occupied.addAll(room.entireRoom);
        startingPool.removeAll(room.entireRoom);
        rooms.add(room);

        return room;
    }
}
