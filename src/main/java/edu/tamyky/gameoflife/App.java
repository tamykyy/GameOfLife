package edu.tamyky.gameoflife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Conway's Game of Life");
        stage.setMinWidth(460);
        stage.setMinHeight(300);
        showScene(stage, "menu-view.fxml");
    }

    public static void showScene(Stage stage, String resource) throws IOException {
        Pane pane = loadPane(App.class.getResource(resource));
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static Pane loadPane(URL location) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
