package byog.Core;

import byog.TileEngine.Tileset;
import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class World {
    private static int WIDTH;
    private static int HEIGHT;
    private static long SEED;
    private static Random RANDOM;
    private static TETile[][] world;

    public World(int wid, int hei, long seed) {
        WIDTH = wid;
        HEIGHT = hei;
        SEED = seed;
        RANDOM = new Random(SEED);
        world = new TETile[WIDTH][HEIGHT];
    }

    public TETile[][] worldGenerator() {
        Position[][] coordinate = new Position[WIDTH][HEIGHT];
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
            makeRoom(coordinate, occupied, startingPool, rooms);
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

        return world;

    }

    private Room makeRectangle(Position p, Position[][] coordinate) {
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

    public Room makeRoom(Position[][] coordinate, Set occupied, ArrayList startingPool, Set rooms) {
        //make a new room
        Position p = Position.randomPoint(startingPool, RANDOM);
        Room room = makeRectangle(p, coordinate);

        while (isOverlapped(occupied, room)) {
            //remake the room
            p = Position.randomPoint(startingPool, RANDOM);
            room = makeRectangle(p, coordinate);
        }

        room.drawRoom(world);


        occupied.addAll(room.entireRoom);
        startingPool.removeAll(room.entireRoom);
        rooms.add(room);

        return room;
    }
}
