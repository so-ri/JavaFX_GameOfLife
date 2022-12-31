package uzh.gameoflife.ModelControl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import uzh.gameoflife.Cell.cellStatus;


class JavaFXMainTest extends ApplicationTest {
    private JavaFXMain app;
    private GameController gc;
    private Player player;

    @Override
    public void start(Stage stage) {
        app = new JavaFXMain();
        gc = GameController.getInstance();
        player = new Player();
        player.receiveName("testGuy");
        app.start(stage);
    }


    /**
     *
     *
     * Tests for Game Over
     * <p>
     * */


    @Test
    public void isOver(){
        Platform.runLater(() -> {
            gc.login("p1","p2", app);
            GameController.updateNumCells(10,0);
            app.updateGrid(false,player);

            String actual = ((Label) app.layout.getChildren().get(0)).getText();
            String expected = gc.whoHasWon() + " HAS WON!";
            assertEquals(expected,actual);
        });
    }

    @Test
    public void isOver_2(){
        Platform.runLater(() -> {
            gc.login("p1","p2", app);
            GameController.updateNumCells(0,10);
            app.updateGrid(false,player);

            String actual = ((Label) app.layout.getChildren().get(0)).getText();
            String expected = gc.whoHasWon() + " HAS WON!";
            assertEquals(expected,actual);
        });
    }

    /**
     *
     *
     * Tests for Login
     * <p>
     * */

    @Test
    public void test_login() {
        Platform.runLater(() -> {
            TextField s1 = (TextField) app.layout.getChildren().get(1);
            s1.setText("Hans");
            Event.fireEvent(app.layout.getChildren().get(4), new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));

            String actual = ((Label) app.layout.getChildren().get(5)).getText();
            String expected = "enter a name";
            assertEquals(expected,actual);
        });
    }

    @Test
    public void test_login2() {
        Platform.runLater(() -> {
            TextField s2 = (TextField) app.layout.getChildren().get(3);
            s2.setText("Hans2");

            Event.fireEvent(app.layout.getChildren().get(4), new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));

            String actual = ((Label) app.layout.getChildren().get(5)).getText();
            String expected = "enter a name";
            assertEquals(expected,actual);
        });
    }

    @Test
    public void test_login4() {
        Platform.runLater(() -> {

            TextField s1 = (TextField) app.layout.getChildren().get(1);
            s1.setText("Hans");

            TextField s2 = (TextField) app.layout.getChildren().get(3);
            s2.setText("Hans2");
            GameController.updateNumCells(12,12);
            Event.fireEvent(app.layout.getChildren().get(4), new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));

            String expected = "Player: Hans has their turn: your color is blue";
            String actual = ((Label) app.layout.getChildren().get(0)).getText();
            assertEquals(expected,actual);
        });
    }


    /**
    *
    *
    * Tests for Input validation
    * <p>
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
            Pane pane = (Pane) app.grid.getChildren().get(21*50+25);
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
            pane = (Pane) app.grid.getChildren().get(45*50+41);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            String actual = ((Label) app.layout.getChildren().get(0)).getText();
            String expected = "You already spawned a cell";
            assertEquals(expected,actual);
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


    /**
     *
     * For the large if else tree
     * <p>
     * */

    @Test
    public void BlueClicksDeadAndClicksRed() {
        boolean blue = true;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            //gets Pane at node at (x,y)
            Pane pane = (Pane) app.grid.getChildren().get(49*50+40);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            pane = (Pane) app.grid.getChildren().get(36*50+23);
            Event.fireEvent(pane, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0,
                    0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true,
                    true, true, true, true, true, true, null));
            assertTrue(player.moveDone());
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
        int x = 31;
        int y = 25;
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
        int x = 32;
        int y = 25;
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
