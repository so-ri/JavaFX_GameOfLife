module uzh.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens uzh.gameoflife to javafx.fxml;
    exports uzh.gameoflife;
    exports uzh.gameoflife.ModelControl;
    opens uzh.gameoflife.ModelControl to javafx.fxml;
}