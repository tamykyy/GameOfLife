package edu.tamyky.gameoflife.game;

import javafx.scene.Cursor;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class Game {

    private static Game instance;
    private Cell[][] world;

    private Game() {
    }

    public void init(GridPane gridPane, GameSize size) {
        world = new Cell[size.getNumRows()][size.getNumCols()];

        for (int row = 0; row < size.getNumRows(); row++) {
            for (int col = 0; col < size.getNumCols(); col++) {
                Rectangle rectangle = createRectangle();
                world[row][col] = new Cell(rectangle);
                gridPane.add(rectangle, col, row);
            }
        }
    }

    public void updateCell(Rectangle rectangle) {
        int row = GridPane.getRowIndex(rectangle);
        int col = GridPane.getColumnIndex(rectangle);
        updateWorld(world[row][col]);
    }

    public void doIteration() {
        List<Cell> cellsToUpdate = new ArrayList<>();
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[row].length; col++) {
                if (checkNeighbors(row, col))
                    cellsToUpdate.add(world[row][col]);
            }
        }
        cellsToUpdate.forEach(this::updateWorld);
    }

    private void updateWorld(Cell cell) {
        if (cell.isAlive()) {
            cell.setAlive(false);
            cell.getRectangle().setFill(Color.WHITE);
        } else {
            cell.setAlive(true);
            cell.getRectangle().setFill(Color.BLACK);
        }
    }

    private boolean checkNeighbors(int row, int col) {
        int liveNeighbors = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) continue;

                try {
                    if (world[i][j].isAlive())
                        liveNeighbors++;
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        }
        return Rules.check(world[row][col].isAlive(), liveNeighbors);
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle(15, 15);
        rectangle.setFill(Color.WHITE);
        rectangle.setCursor(Cursor.HAND);
        rectangle.setStroke(Color.BLACK);
        return rectangle;
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }
}
