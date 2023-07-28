package edu.tamyky.gameoflife.controllers;

import edu.tamyky.gameoflife.App;
import edu.tamyky.gameoflife.game.GameSize;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private ChoiceBox<GameSize> choiceBox;

    @FXML
    protected void startButtonPressed(ActionEvent event) throws IOException {
        GameController.setSize(choiceBox.getValue());
        App.showScene((Stage) (((Node) event.getSource()).getScene()).getWindow(), "game-view.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.setItems(FXCollections.observableArrayList(GameSize.Small, GameSize.Medium, GameSize.Big));
        choiceBox.setValue(GameSize.Medium);
    }
}
