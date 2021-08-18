package byog.Core;

import byog.TileEngine.TETile;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game();
        TETile[][] worldState = game.playWithInputString("n5197880843569031643s");

        Game game2 = new Game();
        TETile[][] worldState2 = game2.playWithInputString("n5197880843569031s");

        System.out.println(worldState.equals(worldState2));
        String s1 = TETile.toString(worldState);
        String s2 = TETile.toString(worldState2);
        System.out.println(s1.equals(s2));


        Game game3 = new Game();
        TETile[][] worldState3 = game3.playWithInputString("n5197880843569031643s");

        Game game4 = new Game();
        TETile[][] worldState4 = game4.playWithInputString("n5197880843569031643s");

        System.out.println(worldState3.equals(worldState4));
        String s3 = TETile.toString(worldState3);
        String s4 = TETile.toString(worldState4);
        System.out.println(s3.equals(s4));
    }
}
