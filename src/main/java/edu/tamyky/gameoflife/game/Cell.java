package edu.tamyky.gameoflife.game;

import javafx.scene.shape.Rectangle;

public class Cell {

    private boolean alive;

    private final Rectangle rectangle;

    public Cell(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
