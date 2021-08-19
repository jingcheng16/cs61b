package byog.Core;

import byog.TileEngine.Tileset;
import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class World {
    private static int WIDTH;
    private static int HEIGHT;
    private Random RANDOM;
    private TETile[][] worldFrame;
    private Position[][] coordinate;
    private Set<Room> rooms;
    private Set<Position> occupied;
    private ArrayList<Position> startingPool;

    public World(int wid, int hei, long seed) {
        this.WIDTH = wid;
        this.HEIGHT = hei;
        this.RANDOM = new Random(seed);
        this.worldFrame = new TETile[WIDTH][HEIGHT];
        this.coordinate = new Position[wid][hei];
        this.rooms = new LinkedHashSet<>();
        this.occupied = new LinkedHashSet<>();
        this.startingPool = new ArrayList<>();

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                worldFrame[x][y] = Tileset.NOTHING;
                coordinate[x][y] = new Position(x, y);
                if (x >= (WIDTH - 3) || y >= (HEIGHT - 3)) {
                    continue;
                } else {
                    startingPool.add(coordinate[x][y]);
                }
            }
        }
    }

    public TETile[][] getWorldFrame() {
        return this.worldFrame;
    }

    public void drawWorld() {
        int roomNumber = RandomUtils.uniform(RANDOM, 30, 45);

        for (int i = 0; i < roomNumber; i++) {
            makeRoom();
        }

        for (Room r: rooms) {
            int count = 0;
            int[] list = {0, 1, 2, 3};
            RandomUtils.shuffle(RANDOM, list);
            for (int side: list) {
                boolean tryBranch = r.makeBranch(side, coordinate, worldFrame, RANDOM);
                if (tryBranch) {
                    count++;
                }
                if (count == 2) {
                    break;
                }
            }
        }

    }

    private Room makeRectangle(Position p) {
        int heightLimit = HEIGHT - p.y;
        int widthLimit = WIDTH - p.x;
        int width;
        int height;

        if (heightLimit > 10) {
            heightLimit = 10;
        }
        height = 3;
        if (heightLimit != 3) {
            height = RandomUtils.uniform(this.RANDOM, 3, heightLimit);
        }
        if (widthLimit > 10) {
            widthLimit = 10;
        }
        width = 3;
        if (widthLimit != 3) {
            width = RandomUtils.uniform(this.RANDOM, 3, widthLimit);
        }

        //initialize new room
        Room room = new Room(p, width, height, this.coordinate);
        return room;
    }

    //@source https://stackoverflow.com/questions/8708542/something-like-contains-any-for-java-set
    //check if the new generated Room have any overlap with the existing room.
    public static boolean isOverlapped(Set occupied, Room newRoom) {
        return (occupied.stream().anyMatch(newRoom.entireRoom::contains));
    }

    public Room makeRoom() {
        //make a new room
        Position p = Position.randomPoint(this.startingPool, this.RANDOM);
        Room room = makeRectangle(p);

        while (isOverlapped(this.occupied, room)) {
            //remake the room
            p = Position.randomPoint(this.startingPool, this.RANDOM);
            room = makeRectangle(p);
        }

        room.drawRoom(this.worldFrame);


        this.occupied.addAll(room.entireRoom);
        this.startingPool.removeAll(room.entireRoom);
        this.rooms.add(room);

        return room;
    }
}
