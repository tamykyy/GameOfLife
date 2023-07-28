package edu.tamyky.gameoflife.controllers;

import edu.tamyky.gameoflife.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController implements Initializable {

    private Game game;

    @FXML
    private GridPane gridPane;

    private Map.Entry<Double, Double> clickCoordinate;
    private Map.Entry<Double, Double> gridInitialPosition;

    public void cellClicked(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        MouseButton button = event.getButton();
        if (clickedNode != gridPane && button == MouseButton.PRIMARY) {
            game.updateCell((Rectangle) clickedNode);
        }
    }

    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE -> game.doIteration();
            default -> System.out.println("just click");
        }
    }

    public void onMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.MIDDLE) {
            clickCoordinate = Map.entry(event.getSceneX(), event.getSceneY());
            gridInitialPosition = Map.entry(gridPane.getTranslateX(), gridPane.getTranslateY());
        }
    }

    public void onMouseDragged(MouseEvent event) {
        gridPane.setCursor(Cursor.CLOSED_HAND);
        MouseButton button = event.getButton();
        if (button == MouseButton.MIDDLE) {
            System.out.println(gridPane.getScaleZ());

            gridPane.setScaleZ(gridPane.getScaleZ() + 10);
            gridPane.setTranslateX(gridInitialPosition.getKey() - (clickCoordinate.getKey() - event.getSceneX()));
            gridPane.setTranslateY(gridInitialPosition.getValue() - (clickCoordinate.getValue() - event.getSceneY()));
        }
    }

    private boolean isPlaying;
    private int speed;
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void stopButtonClicked(ActionEvent event) {
        isPlaying = !isPlaying;
    }

    public void speed1ButtonClicker(ActionEvent actionEvent) throws InterruptedException {
        isPlaying = true;
        speed = 500;
        executorService.execute(gameRunnable);
    }

    public void speed2ButtonClicker(ActionEvent actionEvent) throws InterruptedException {
        isPlaying = true;
        speed = 150;
        executorService.execute(gameRunnable);
    }

    public void speed3ButtonClicker(ActionEvent actionEvent) throws InterruptedException {
        isPlaying = true;
        speed = 1;
        executorService.execute(gameRunnable);
    }


    Runnable gameRunnable = () -> {
        while (isPlaying) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            game.doIteration();
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = Game.getInstance();
        game.init(gridPane);
    }

    public static void shutdownExecutorService() {
        executorService.shutdownNow();
        System.out.println(executorService.isShutdown());
    }
}
