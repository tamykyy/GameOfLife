package edu.tamyky.gameoflife.game;

import javafx.scene.Cursor;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {

    private static Game instance;
    
    private boolean[][] worldData;

    private Game() {
    }

    public void init(GridPane world) {
        int rows = 100;
        int cols = 100;

        worldData = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                world.add(createRectangle(), i, j);
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

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }
}
