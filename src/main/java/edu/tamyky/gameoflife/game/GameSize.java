package edu.tamyky.gameoflife.game;

public enum GameSize {
    Big(50, 50), Medium(30, 30), Small(15, 15);

    private final int numRows;
    private final int numCols;

    GameSize(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}
