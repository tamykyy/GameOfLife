module edu.tamyky.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.tamyky.gameoflife to javafx.fxml;
    exports edu.tamyky.gameoflife;
    exports edu.tamyky.gameoflife.controllers;
    opens edu.tamyky.gameoflife.controllers to javafx.fxml;
}