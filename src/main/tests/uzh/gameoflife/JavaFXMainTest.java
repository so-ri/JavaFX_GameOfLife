package uzh.gameoflife;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import uzh.gameoflife.ModelControl.GameController;
import uzh.gameoflife.ModelControl.Player;

public class JavaFXMainTest extends ApplicationTest {
    private JavaFXMain app;
    private GameController controller;
    private Player player;

    @Override
    public void start(Stage stage) {
        app = new JavaFXMain();
        controller = GameController.getInstance();
        player = new Player();
        player.receiveName("player1");
        app.updateGrid(true, player);
        stage.setScene(app.getScene());
        stage.show();
    }

    @Test
    public void testUpdateGrid() {
        // Verify that the "Player: player1 has their turn: your color is blue" label is displayed
        FxRobot robot = new FxRobot();
        robot.lookup("#label").queryAs(Label.class).isVisible();

        // Verify that the "Points of Blue Player: 0 Points of Red Player: 0" label is displayed
        robot.lookup("#points").queryAs(Label.class).isVisible();

        // Verify that the grid is displayed
        robot.lookup("#grid").queryAs(GridPane.class).isVisible();
    }
}
