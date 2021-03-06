package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Game {
    private TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    //private static long SEED = 2873123;
    //private static Random RANDOM = new Random(SEED);

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
        World world = new World(WIDTH, HEIGHT, value);
        world.drawWorld();
        TETile[][] tileWorld = world.getWorldFrame();

        // draws the world to the screen
        //ter.renderFrame(world);

        //return finalWorldFrame;
        return tileWorld;
    }

}
