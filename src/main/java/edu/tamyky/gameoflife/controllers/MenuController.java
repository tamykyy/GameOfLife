package edu.tamyky.gameoflife.controllers;

import edu.tamyky.gameoflife.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    protected void startButtonPressed(ActionEvent event) throws IOException {
        App.showScene((Stage) (((Node) event.getSource()).getScene()).getWindow(), "game-view.fxml");
    }

}
