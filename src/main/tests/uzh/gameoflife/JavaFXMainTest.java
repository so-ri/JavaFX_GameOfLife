

package uzh.gameoflife;
import static java.nio.file.Files.find;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.assertj.javafx.api.Assertions.assertThat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import uzh.gameoflife.Cell.cellStatus;
import uzh.gameoflife.ModelControl.GameController;
import uzh.gameoflife.ModelControl.Player;



class JavaFXMainTest extends ApplicationTest {
    private JavaFXMain app;
    private GameController gc;
    private Player player;

    @Override
    public void start(Stage stage) throws Exception {
        app = new JavaFXMain();
        gc = GameController.getInstance();
        player = new Player();
        player.receiveName("testGuy");
        app.start(stage);
    }
    @Test
    public void test_updateGrid_RedPlayer() {
        // given
        boolean blue = false;
        player.hasKilledEnemy = true;
        player.spawnedCell = true;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);
        });


        // then
        //Assertions.assertThat(app.layout).getChildren(1);
        String actual = ((Label) app.layout.getChildren().get(0)).getText();
        String expected = "Name of the first player:";
        assertEquals(expected,actual);
    }

    @Test
    public void test_updateGrid_BluePlayer() {
        // given
        boolean blue = true;
        player.hasKilledEnemy = true;
        player.spawnedCell = true;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);
        });



        // then
        //Assertions.assertThat(app.layout).getChildren(1);
        String actual = ((Label) app.layout.getChildren().get(0)).getText();
        String expected = "Name of the first player:";
        assertEquals(expected,actual);
    }

    @Test
    public void test_updateGrid_Red_clicks_Dead() {
        app = new JavaFXMain();
        boolean blue = false;
        int x = 5;
        int y = 5;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(x*50+y);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            assertEquals(cellStatus.RED,gc.getStatus(x,y));
        });
    }

    @Test
    public void test_updateGrid_Red_clicks_Blue_Twice() {
        app = new JavaFXMain();
        boolean blue = false;
        int x = 15;
        int y = 24;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(x*50+y);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            assertEquals(cellStatus.RED,gc.getStatus(x,y));
        });
    }

    @Test
    public void test_updateGrid_Red_clicks_Blue() {
        app = new JavaFXMain();
        boolean blue = false;
        int x = 15;
        int y = 24;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(x*50+y);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            assertEquals(cellStatus.DEAD,gc.getStatus(x,y));
        });
    }

    @Test
    public void test_updateGrid_Blue_clicks_Dead() {
        app = new JavaFXMain();
        boolean blue = true;
        int x = 5;
        int y = 5;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(x*50+y);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            assertEquals(cellStatus.BLUE,gc.getStatus(x,y));
        });
    }

    @Test
    public void test_updateGrid_Blue_clicks_Red_Twice() {
        app = new JavaFXMain();
        boolean blue = true;
        int x = 35;
        int y = 24;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(x*50+y);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            assertEquals(cellStatus.BLUE,gc.getStatus(x,y));
        });
    }

    @Test
    public void test_updateGrid_Blue_clicks_Red() {
        app = new JavaFXMain();
        boolean blue = true;
        int x = 35;
        int y = 24;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(x*50+y);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            assertEquals(cellStatus.DEAD,gc.getStatus(x,y));
        });
    }

}
