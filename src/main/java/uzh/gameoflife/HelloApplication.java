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
    VBox gridLayout = new VBox();
    @Override
    public void start(Stage primaryStage){
        GameController g1 = GameController.getInstance();

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
        VBox layout = new VBox();
        layout.getChildren().addAll(label0, textField_name_0);
        layout.setSpacing(10);
        layout.getChildren().addAll(label1, textField_name_1);
        layout.setSpacing(10);
        layout.getChildren().addAll(loginbtn, errorLabel);

        //setting up new scene and show it
        Scene scene = new Scene(layout, 1000, 1000);

        primaryStage.setScene(scene);
        primaryStage.show();
        /*end login */

        /*
        * create second grid layout
        * */


        Color newColor = Color.GREY;
        Background background = new Background(new BackgroundFill(newColor, CornerRadii.EMPTY, Insets.EMPTY));
        layout.setBackground(background);
        gridLayout.setBackground(background);




        /*
        GridPane grid = new GridPane();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                // create a white rectangle and add it to the grid
                Rectangle rect = new Rectangle(25, 25);
                rect.setStyle("-fx-fill: red; -fx-stroke: grey; -fx-stroke-width: 3;-fx-border-radius: 10;");
                grid.add(rect,i,j);
            }
        }
        gridLayout.getChildren().add(grid);

         */


        /*
        *
        * flowhandling
        *
        * */

        //login button to second grid layout
        loginbtn.setOnAction(e -> {
            String txtfield0 = textField_name_0.getText();
            String txtfield1 = textField_name_1.getText();

            if (txtfield0.isEmpty() || txtfield1.isEmpty()) errorLabel.setText("enter something meaningful");
            else {
                g1.receiveName0(txtfield0);
                g1.receiveName1(txtfield1);
                g1.startGame();
                scene.setRoot(gridLayout);
            }
        });
        gridLayout.getChildren().add(updateGrid(g1));
    }

    public static void main(String[] args) {
        launch();
    }

    private GridPane updateGrid(GameController g1){

        gridLayout.getChildren().clear();
        GridPane grid = new GridPane();

        for(short x = 0; x < 50; x++){
            for(short y = 0; y < 50; y++){

                cellStatus value = g1.getStatus(x,y);
                Pane pane = new Pane();
                pane.setPrefSize(1000,1000);

                switch (value){
                    case RED -> pane.setStyle("-fx-background-color: RED");
                    case BLUE -> pane.setStyle("-fx-background-color: BLUE");
                    case DEAD -> pane.setStyle("-fx-background-color: WHITE");
                }

                short finalX = x;
                short finalY = y;
                pane.setOnMouseClicked(event -> {
                    //Object node = event.getSource(); this would return the current node on which the event was fired upon
                    g1.changeCellStatus(finalX, finalY, cellStatus.RED);
                });

                grid.add(pane,x,y);
            }
        }
        return grid;
    }
}