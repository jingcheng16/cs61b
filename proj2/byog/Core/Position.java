package byog.Core;

import byog.TileEngine.Tileset;
import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.Random;

public class Position {
    int x;
    int y;
    //status is 0 if the position is unoccupied, 1 vice versa.
    int status;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = 0;
    }

    private Position below(Position[][] coordinate) {
        if (this.y != 0) {
            return coordinate[this.x][this.y - 1];
        } else {
            return null;
        }
    }

    private Position above(Position[][] coordinate) {
        if (this.y != coordinate[0].length - 1) {
            return coordinate[this.x][this.y + 1];
        } else {
            return null;
        }
    }

    private Position left(Position[][] coordinate) {
        if (this.x != 0) {
            return coordinate[this.x - 1][this.y];
        } else {
            return null;
        }
    }

    private Position right(Position[][] coordinate) {
        if (this.x != coordinate.length - 1) {
            return coordinate[this.x + 1][this.y];
        } else {
            return null;
        }
    }

    public Position nextP(Position[][] coordinate, int direction) {
        switch (direction) {
            case 0:
                return this.below(coordinate);
            case 1:
                return this.above(coordinate);
            case 2:
                return this.left(coordinate);
            case 3:
                return this.right(coordinate);
            default:
                return null;
        }
    }

    public void drawCombo(int direction, Position[][] coordinate, TETile[][] world) {
        switch (direction) {
            case 0:
            case 1:
                if (coordinate[this.x - 1][this.y].status == 0) {
                    world[this.x - 1][this.y] = Tileset.WALL;
                    coordinate[this.x - 1][this.y].status = 1;
                }
                if (coordinate[this.x + 1][this.y].status == 0) {
                    world[this.x + 1][this.y] = Tileset.WALL;
                    coordinate[this.x + 1][this.y].status = 1;
                }
                world[this.x][this.y] = Tileset.FLOOR;
                coordinate[this.x][this.y].status = 1;
                break;
            case 2:
            case 3:
                if (coordinate[this.x][this.y - 1].status == 0) {
                    world[this.x][this.y - 1] = Tileset.WALL;
                    coordinate[this.x][this.y - 1].status = 1;
                }
                if (coordinate[this.x][this.y + 1].status == 0) {
                    world[this.x][this.y + 1] = Tileset.WALL;
                    coordinate[this.x][this.y + 1].status = 1;
                }
                world[this.x][this.y] = Tileset.FLOOR;
                coordinate[this.x][this.y].status = 1;
                break;
            default:
                break;
        }
    }

    public boolean isEdge(Position[][] coordinate) {
        int width = coordinate.length;
        int height = coordinate[0].length;
        return (this.x == 0 || this.x == width - 1 || this.y == 0 || this.y == height - 1);
    }

    public static Position randomPoint(ArrayList space, Random r) {
        // the max starting point should not reach the edge
        int i = RandomUtils.uniform(r, 0, space.size());
        Position p = (Position) space.get(i);
        return p;
    }
}
