package byog.Core;

import byog.TileEngine.Tileset;
import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;




public class Room {
    Position startingPoint;
    int width;
    int height;
    int lowerY;
    int upperY;
    int leftX;
    int rightX;
    //perimeter element
    // first: lower width
    // second: upper width
    // third: left width
    // fourth: right width
    ArrayList<ArrayList<Position>> perimeter = new ArrayList<>();

    //Set<Position> perimeter = new HashSet<>();
    Set<Position> inner = new HashSet<>();
    Set<Position> entireRoom = new HashSet<>();

    /**
     * Room constructor
     * @param point the lower left corner point
     * @param wid the width of the rectangle room
     * @param hei the height of the rectangle room
     */
    public Room(Position point, int wid, int hei, Position[][] coordinate) {
        this.startingPoint = point;
        this.width = wid;
        this.height = hei;
        //define the boundary
        this.lowerY = point.y;
        this.upperY = lowerY + height - 1;
        this.leftX = point.x;
        this.rightX = point.x + width - 1;


        //include all points on perimeter
        ArrayList<Position> lowerWidth = new ArrayList<>();
        ArrayList<Position> upperWidth = new ArrayList<>();
        ArrayList<Position> leftHeight = new ArrayList<>();
        ArrayList<Position> rightHeight = new ArrayList<>();
        for (int i = leftX; i <= rightX; i++) {
            lowerWidth.add(coordinate[i][lowerY]);
            upperWidth.add(coordinate[i][upperY]);
        }
        for (int i = lowerY; i <= upperY; i++) {
            leftHeight.add(coordinate[leftX][i]);
            rightHeight.add(coordinate[rightX][i]);
        }
        perimeter.add(lowerWidth);
        perimeter.add(upperWidth);
        perimeter.add(leftHeight);
        perimeter.add(rightHeight);


        //include all points inner
        for (int i = leftX + 1; i < rightX; i++) {
            for (int j = lowerY + 1; j < upperY; j++) {
                inner.add(coordinate[i][j]);
            }
        }

        for (int i = 0; i < 4; i++) {
            entireRoom.addAll(perimeter.get(i));
        }
        entireRoom.addAll(inner);
    }

    private void drawPerimeter(TETile[][] world) {
        for (ArrayList<Position> side: perimeter) {
            for (Position p: side) {
                world[p.x][p.y] = Tileset.WALL;
                p.status = 1;
            }
        }
    }

    private void drawInner(TETile[][] world) {
        for (Position p: inner) {
            world[p.x][p.y] = Tileset.FLOOR;
            p.status = 1;
        }
    }

    public void drawRoom(TETile[][] world) {
        drawPerimeter(world);
        drawInner(world);
    }

    public void makeBranch(Position[][] coordinate, TETile[][] world, Random RANDOM) {
        int side = RandomUtils.uniform(RANDOM, 0, 4);
        int length = this.perimeter.get(side).size();
        int p = RandomUtils.uniform(RANDOM, 1, length - 1);
        Position middleP = this.perimeter.get(side).get(p);

        while (middleP.isEdge(coordinate)) {
            side = RandomUtils.uniform(RANDOM, 0, 4);
            length = this.perimeter.get(side).size();
            p = RandomUtils.uniform(RANDOM, 1, length - 1);
            middleP = this.perimeter.get(side).get(p);
        }
        middleP.drawCombo(side, coordinate, world);
        while (middleP.nextP(coordinate, side).status != 1) {
            middleP = middleP.nextP(coordinate,side);
            middleP.drawCombo(side, coordinate, world);
            if (middleP.isEdge(coordinate)) {
                break;
            }
        }
        if (middleP.isEdge(coordinate)) {
            world[middleP.x][middleP.y] = Tileset.WALL;
        } else {
            Position endP = middleP.nextP(coordinate, side);
            endP.drawCombo(side, coordinate, world);
        }
    }

}
