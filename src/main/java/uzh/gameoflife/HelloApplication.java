package uzh.gameoflife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;
import org.controlsfx.validation.ValidationResult;
import uzh.gameoflife.Cell.cellStatus;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    private GameController g1 = GameController.getInstance();
    private VBox layout = new VBox();


    @Override
    public void start(Stage primaryStage){

        //setting up new scene
        primaryStage.setScene(new Scene(layout,700,700 ));
        login(); // takes name via login and starts grid
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch();
    }

    public void updateGrid(Boolean blue, Player player){
        player.spawnedCell = false;
        player.hasKilledEnemy = false;
        layout.getChildren().clear();
        GridPane grid = new GridPane();
        Label label = new Label("Label");
        label.setTextFill(Color.RED);
        label.setBackground(Background.fill(Color.LIGHTPINK));

        for(int x = 0; x < 50; x++){
            for(int y = 0; y < 50; y++){

                cellStatus value = g1.getStatus(x,y);
                Pane pane = new Pane();
                pane.setPrefSize(1000,1000);

                switch (value){
                    case RED -> pane.setStyle("-fx-background-color: RED");
                    case BLUE -> pane.setStyle("-fx-background-color: BLUE");
                    case DEAD -> pane.setStyle("-fx-background-color: WHITE");
                }

                int finalX = x;
                int finalY = y;
                pane.setOnMouseClicked(event -> {

                    //Checks friendly fire

                    if((blue && value == cellStatus.BLUE) || !blue && value == cellStatus.RED )
                        label.setText("You cant kill your own cell!");
                    else if ((blue && value == cellStatus.RED || !blue && value == cellStatus.BLUE)
                     && player.hasKilledEnemy)
                        label.setText("You already killed an opponent cell");
                    else if (player.spawnedCell && value == cellStatus.DEAD)
                        label.setText("You already spawned a cell");

                    else if (!blue){
                        if(value == cellStatus.DEAD) {
                            g1.changeCellStatus(finalX, finalY, cellStatus.RED);
                            player.spawnedCell = true;
                        }
                        else {
                            g1.changeCellStatus(finalX, finalY, cellStatus.DEAD);
                            player.hasKilledEnemy = true;
                        }
                        if(player.moveDone()) g1.blueMove(this);
                        System.out.println("changed to red");
                        System.out.println(finalX + " " + finalY);
                    }
                    else if (blue){
                        if(value == cellStatus.DEAD) {
                            g1.changeCellStatus(finalX, finalY, cellStatus.BLUE);
                            player.spawnedCell = true;
                        }
                        else {
                            g1.changeCellStatus(finalX, finalY, cellStatus.DEAD);
                            player.hasKilledEnemy = true;
                        }
                        if(player.moveDone()) g1.redMove(this);
                        System.out.println("changed to blue");
                        System.out.println(finalX + " " + finalY);
                    }

                });
                grid.add(pane,x,y);
            }
        }
        grid.setGridLinesVisible(true);
        layout.getChildren().addAll(label,grid);

    }

    private void login(){

        /*
         * Layout & scene for login
         * */
        //label + textfield of player 0
        Label label0 = new Label("Name Spieler blau:");
        TextField textField_name_0 = new TextField();

        //label + textfield of player 1
        Label label1 = new Label("Name Spieler rot:");
        TextField textField_name_1 = new TextField();

        //button for sending in both names
        Button loginbtn = new Button("LOS");

        //errorlabel
        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.RED);

        //new login layout & adding all elements onto it

        layout.getChildren().addAll(label0, textField_name_0);
        layout.setSpacing(10);
        layout.getChildren().addAll(label1, textField_name_1);
        layout.setSpacing(10);
        layout.getChildren().addAll(loginbtn, errorLabel);


        //login button to second grid layout

        loginbtn.setOnAction(e -> {
            String txtfield0 = textField_name_0.getText();
            String txtfield1 = textField_name_1.getText();

            if (txtfield0.isEmpty() || txtfield1.isEmpty()) errorLabel.setText("enter something meaningful");
            else {
                g1.login(txtfield0,txtfield1,this);
            }
        });
}


}