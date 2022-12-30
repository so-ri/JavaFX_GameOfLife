

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


    /*
    *
    *
    * FOR THE ERROR STATEMENTS
    *
    * */
    @Test
    public void test_killOwnCell(){
        boolean blue = false;
        int x = 36;
        int y = 25;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(x*50+y);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            String actual = ((Label) app.layout.getChildren().get(0)).getText();
            String expected = "You cant kill your own cell!";
            assertEquals(expected,actual);
        });
    }

    @Test
    public void test_updateGrid_Red_clicks_different_Blues() {
        boolean blue = false;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(23*50+24);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            pane = (Pane) app.grid.getChildren().get(21*50+23);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));

            String actual = ((Label) app.layout.getChildren().get(0)).getText();
            String expected = "You already killed an opponent cell";
            assertEquals(expected,actual);
        });
    }
    @Test
    public void test_updateGrid_Blue_clicks_different_Reds() {
        boolean blue = true;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(31*50+23);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            pane = (Pane) app.grid.getChildren().get(32*50+23);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));

            String actual = ((Label) app.layout.getChildren().get(0)).getText();
            String expected = "You already killed an opponent cell";
            assertEquals(expected,actual);
        });
    }

    @Test
    public void test_updateGrid_Blue_clicks_Dead_twice() {
        boolean blue = true;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(45*50+40);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            pane = (Pane) app.grid.getChildren().get(45*50+40);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
        });
    }

    @Test
    public void test_killOwnCell_blue(){
        boolean blue = true;
        int x = 17;
        int y = 25;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(x*50+y);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            String actual = ((Label) app.layout.getChildren().get(0)).getText();
            String expected = "You cant kill your own cell!";
            assertEquals(expected,actual);
        });
    }

    @Test
    public void test_updateGrid_Red_clicks_Dead() {
        boolean blue = false;
        int x = 25;
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
        boolean blue = false;
        int x = 20;
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
        boolean blue = true;
        int x = 30;
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
