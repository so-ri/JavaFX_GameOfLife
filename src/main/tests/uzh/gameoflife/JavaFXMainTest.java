package uzh.gameoflife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.osgi.service.TestFx;
import uzh.gameoflife.ModelControl.Player;

import static org.junit.jupiter.api.Assertions.*;

class JavaFXMainTest extends ApplicationTest {

    JavaFXMain testMain;
    Player tester;
    boolean blue;

    @BeforeEach
    public void setup(){
        testMain = new JavaFXMain();
        tester = new Player();
        tester.receiveName("tester");
        blue = true;
    }

    @Override
    public void start(Stage primaryStage){
        System.out.println("test");
        VBox layout = new VBox();
        //setting up new scene
        primaryStage.setScene(new Scene(layout,700,700 ));
        primaryStage.show();
        testMain.start(primaryStage);
    }


    @Test
    void main() {
    }

    @Test
    void updateGrid() {

    }

    @Test
    void labelTest(){
        testMain.updateGrid(blue, tester);
        Label label2 = lookup("#label").query();
        assertEquals("Player: tester has their turn: your color is blue", label2.getText());
    }
}