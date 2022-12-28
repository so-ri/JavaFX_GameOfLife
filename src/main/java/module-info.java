module uzh.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens uzh.gameoflife to javafx.fxml;
    exports uzh.gameoflife;
}