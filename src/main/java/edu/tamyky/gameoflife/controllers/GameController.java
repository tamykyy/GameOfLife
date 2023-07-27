package edu.tamyky.gameoflife.controllers;

import edu.tamyky.gameoflife.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private Game game;

    @FXML
    private GridPane gridPane;

    public void cellClicked(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != gridPane) {
            game.updateCell((Rectangle) clickedNode);
        }
    }

    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE -> game.doIteration();
            default -> System.out.println("just click");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = Game.getInstance();
        game.init(gridPane);
    }
}
