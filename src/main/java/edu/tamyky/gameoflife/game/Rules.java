package edu.tamyky.gameoflife.game;

public class Rules {

    public static boolean check(boolean cellAlive, int liveNeighbors) {
        if (cellAlive) {
            return liveNeighbors < 2 || liveNeighbors > 3;
        } else {
            return liveNeighbors == 3;
        }
    }
}
