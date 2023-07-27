package edu.tamyky.gameoflife.game;

import javafx.scene.Cursor;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {

    private static Game instance;

    private boolean[][] worldData;

    private GridPane worldGrid;

    private Game() {
    }

    public void init(GridPane worldGrid) {
        this.worldGrid = worldGrid;
        int rows = 10;
        int cols = 10;

        worldData = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                worldGrid.add(createRectangle(), i, j);
            }
        }
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle(15, 15);
        rectangle.setFill(Color.WHITE);
        rectangle.setCursor(Cursor.HAND);
        rectangle.setStroke(Color.BLACK);
        return rectangle;
    }

    public void updateCell(Rectangle cell) {
        int row = GridPane.getRowIndex(cell);
        int col = GridPane.getColumnIndex(cell);
        if (worldData[row][col]) {
            worldData[row][col] = false;
            cell.setFill(Color.WHITE);
        } else {
            worldData[row][col] = true;
            cell.setFill(Color.BLACK);
        }
    }

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }
}
