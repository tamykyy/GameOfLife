package edu.tamyky.gameoflife.controllers;

import edu.tamyky.gameoflife.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private Game game;

    @FXML
    private GridPane world;

    public void clickCell(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != world) {
            game.updateCell((Rectangle) clickedNode);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = Game.getInstance();
        game.init(world);
    }
}
