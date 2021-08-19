package byog.Core;

import byog.TileEngine.Tileset;
import byog.TileEngine.TETile;

import java.util.*;

public class World {
    private static int WIDTH;
    private static int HEIGHT;
    public Random RANDOM;
    public TETile[][] world;
    public Position[][] coordinate;
    public Set<Room> rooms;
    public Set<Position> occupied;
    public ArrayList<Position> startingPool;

    public World(int wid, int hei, long seed) {
        this.WIDTH = wid;
        this.HEIGHT = hei;
        this.RANDOM = new Random(seed);
        this.world = new TETile[WIDTH][HEIGHT];
        this.coordinate = new Position[wid][hei];
        this.rooms = new LinkedHashSet<>();
        this.occupied = new LinkedHashSet<>();
        this.startingPool = new ArrayList<>();

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
    }

    public void drawWorld() {
        int roomNumber = RandomUtils.uniform(RANDOM, 30, 45);
        System.out.println(roomNumber);

        for (int i = 0; i < roomNumber; i++) {
            makeRoom();
        }



        for (Room r: rooms) {
            int count = 0;
            int[] list = {0, 1, 2, 3};
            RandomUtils.shuffle(RANDOM, list);
            for (int side: list) {
                //System.out.print("side: " + side + " count: " + count + " ||");
                boolean tryBranch = r.makeBranch(side, coordinate, world, RANDOM);
                //System.out.println("try: " + tryBranch);
                if (tryBranch) {
                    count++;
                }
                //System.out.print("side: " + side + " count: " + count + " ||");
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

        room.drawRoom(this.world);


        this.occupied.addAll(room.entireRoom);
        this.startingPool.removeAll(room.entireRoom);
        this.rooms.add(room);

        return room;
    }

    public static void main(String[] args) {
        long value = Long.parseLong("n5197880843569031643s".replaceAll("[^0-9]", ""));
        World world1 = new World(80, 30, value);
        world1.drawWorld();
        TETile[][] tileWorld1 = world1.world;
        World world2 = new World(80, 30, value);
        world2.drawWorld();
        TETile[][] tileWorld2 = world2.world;

        System.out.println(tileWorld1.equals(tileWorld2));
        String s1 = TETile.toString(tileWorld1);
        String s2 = TETile.toString(tileWorld2);
        //System.out.println(s1);
        //System.out.println(s2);
        System.out.println(s1.equals(s2));
    }
}
