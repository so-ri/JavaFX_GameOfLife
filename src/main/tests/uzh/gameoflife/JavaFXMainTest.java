

package uzh.gameoflife;
import static java.nio.file.Files.find;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.assertj.javafx.api.Assertions.assertThat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
    public void test_updateGrid_RedPlayer3() {
        // given
        boolean blue = false;
        player.hasKilledEnemy = true;
        player.spawnedCell = true;
        Platform.runLater(() -> {
            app.updateGrid(blue, player);

            Pane pane = (Pane) app.grid.getChildren().get(774);
            pane.requestFocus();
            new FxRobot().clickOn(app.grid.getChildren().get(774));
            System.out.println(gc.getStatus(15,24));
        });



    }
    /**
    @Test
    public void test_updateGrid_BluePlayer2() {

        boolean blue = true;
        GameController.updateNumCells(0,12);
        player.receiveNumCells(0);
        player.receiveName("tester");
        Platform.runLater(() -> {
            app.updateGrid(blue, player);
        });



        // then
        //Assertions.assertThat(app.layout).getChildren(1);
        String actual = ((Label) app.layout.getChildren().get(0)).getText();
        String expected = player.getName() + " HAS WON!";
        assertEquals(expected,actual);
    }
    */
    /*
    @Test
    void labelTest(FxRobot robot){
        //FxRobot robot = new FxRobot();
        app.updateGrid(true, player);
        //Node label2 = robot.lookup("#label").query();
        //assertEquals("Player: tester has their turn: your color is blue", label2.);
        FxAssert.verifyThat("#label", LabeledMatchers.hasText("Player: testGuy has their turn: your color is blue"));
        // Verify that the grid is displayed
        //robot.lookup("#grid").queryAs(GridPane.class).isVisible();
    }



     */
    /*
    @Test
    public void testUpdateGrid_BluePlayer_HasNotKilledEnemy_HasNotSpawnedCell(FxRobot robot) {
        // given
        boolean blue = true;
        app.updateGrid(blue, player);

        // then
        app.layout.getChildren().get(0).get
        Assertions.assertThat(robot.lookup("#label").queryAs()).hasText("click me!");
        Assertions.assertThat( app.layout.getChildren().get(0)).hasText("Player: player1 has their turn: your color is blue");
    }

     */
}
