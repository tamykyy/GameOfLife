package edu.tamyky.gameoflife.controllers;

import edu.tamyky.gameoflife.game.Game;
import edu.tamyky.gameoflife.game.GameSize;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
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

    private static GameSize size;

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
            case SPACE -> stopButtonClicked();
            case DIGIT1 -> speed1ButtonClicked();
            case DIGIT2 -> speed2ButtonClicked();
            case DIGIT3 -> speed3ButtonClicked();
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
        if (event.getButton() == MouseButton.MIDDLE) {
            gridPane.setTranslateX(gridInitialPosition.getKey() - (clickCoordinate.getKey() - event.getSceneX()));
            gridPane.setTranslateY(gridInitialPosition.getValue() - (clickCoordinate.getValue() - event.getSceneY()));
        }
    }

    private boolean isPlaying;
    @FXML
    private int speed;
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @FXML
    private ToggleButton stopButton;

    @FXML
    private ToggleButton x1Button;

    @FXML
    private ToggleButton x2Button;

    @FXML
    private ToggleButton x3Button;

    public void stopButtonClicked() {
        isPlaying = !isPlaying;
        if (isPlaying)
            speed1ButtonClicked();
        else
            stopButton.setSelected(true);
    }

    public void speed1ButtonClicked() {
        x1Button.setSelected(true);
        isPlaying = true;
        speed = 500;
        executorService.execute(gameRunnable);
    }

    public void speed2ButtonClicked() {
        x2Button.setSelected(true);
        isPlaying = true;
        speed = 150;
        executorService.execute(gameRunnable);
    }

    public void speed3ButtonClicked() {
        x3Button.setSelected(true);
        isPlaying = true;
        speed = 30;
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
        game.init(gridPane, size);
    }

    public static void shutdownExecutorService() {
        executorService.shutdownNow();
        System.out.println(executorService.isShutdown());
    }

    public static void setSize(GameSize size) {
        GameController.size = size;
    }
}
