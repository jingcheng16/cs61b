package byog.Core;

import byog.TileEngine.TETile;

public class Launcher {
    public static void main(String[] args){
        Game game = new Game();
        TETile[][] worldState = game.playWithInputString("n5197880843569031643s");

        Game game2 = new Game();
        TETile[][] worldState2 = game.playWithInputString("n5197880843569031643s");

        System.out.println(worldState.equals(worldState2));
        System.out.println(TETile.toString(worldState));
        System.out.println(TETile.toString(worldState2));
    }
}
