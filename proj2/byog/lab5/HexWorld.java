package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    //first line has the number of tiles as size has
    //every next line will has 2 more tiles than the above line has
    //height is the double of size
    int size;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;


    public static void singleHexagon(TETile[][] world, int size, int xPosition, int yPosition, TETile pattern) {
        //x is rowNumber
        for (int x = 0; x < size * 2; x++) {
            int xStart = xPosition + indentation(size,x);
            int rowPosition = yPosition - x;
            int rowLength = lengthOfRow(size, x);
            for (int i = xStart; i < xStart + rowLength; i++) {
                world[i][rowPosition] = pattern;
            }
        }
    }

    //rowNumber start from 0
    private static int lengthOfRow(int size, int rowNumber) {
        int height = size * 2;
        if (rowNumber < size) {
            return size + rowNumber * 2;
        } else {
            return lengthOfRow(size, height - 1 - rowNumber);
        }
    }

    private static int indentation(int size, int rowNumber) {
        int height = size * 2;
        if (rowNumber < size) {
            return size + 1 - rowNumber;
        } else {
            return indentation(size, height - 1 - rowNumber);
        }
    }

    private static void totalHexagon(TETile[][] world, int size, int xPosition, int yPosition) {
        int hMove = size * 2 - 1;
        int vMove = size;
        singleHexagon(world, size, hMove * 2, HEIGHT - 1, Tileset.TREE);
        singleHexagon(world, size, hMove, HEIGHT - 1 - vMove, Tileset.GRASS);
        singleHexagon(world, size, hMove * 3, HEIGHT - 1 - vMove, Tileset.FLOWER);
        singleHexagon(world, size, 0, HEIGHT - 1 - vMove * 2, Tileset.MOUNTAIN);
        singleHexagon(world, size, hMove * 2, HEIGHT - 1 - vMove * 2, Tileset.MOUNTAIN);
        singleHexagon(world, size, hMove * 4, HEIGHT - 1 - vMove * 2, Tileset.FLOWER);
        singleHexagon(world, size, hMove, HEIGHT - 1 - vMove * 3, Tileset.MOUNTAIN);
        singleHexagon(world, size, hMove * 3, HEIGHT - 1 - vMove * 3, Tileset.SAND);
        singleHexagon(world, size, 0, HEIGHT - 1 - vMove * 4, Tileset.GRASS);
        singleHexagon(world, size, hMove * 2, HEIGHT - 1 - vMove * 4, Tileset.MOUNTAIN);
        singleHexagon(world, size, hMove * 4, HEIGHT - 1 - vMove * 4, Tileset.TREE);
        singleHexagon(world, size, hMove, HEIGHT - 1 - vMove * 5, Tileset.MOUNTAIN);
        singleHexagon(world, size, hMove * 3, HEIGHT - 1 - vMove * 5, Tileset.TREE);
        singleHexagon(world, size, 0, HEIGHT - 1 - vMove * 6, Tileset.GRASS);
        singleHexagon(world, size, hMove * 2, HEIGHT - 1 - vMove * 6, Tileset.MOUNTAIN);
        singleHexagon(world, size, hMove * 4, HEIGHT - 1 - vMove * 6, Tileset.SAND);
        singleHexagon(world, size, hMove, HEIGHT - 1 - vMove * 7, Tileset.FLOWER);
        singleHexagon(world, size, hMove * 3, HEIGHT - 1 - vMove * 7, Tileset.MOUNTAIN);
        singleHexagon(world, size, hMove * 3, HEIGHT - 1 - vMove * 7, Tileset.MOUNTAIN);
        singleHexagon(world, size, hMove * 2, HEIGHT - 1 - vMove * 8, Tileset.MOUNTAIN);

    }

    public static void main(String[] args) {

        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        totalHexagon(world, 3, 0, HEIGHT - 1);

        // draws the world to the screen
        ter.renderFrame(world);



    }

}
