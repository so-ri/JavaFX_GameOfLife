package uzh.gameoflife;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uzh.gameoflife.Cell.Neighbors;
import uzh.gameoflife.Cell.cellStatus;

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

        //reset those values for every function scope
        player.spawnedCell = false;
        player.hasKilledEnemy = false;

        //reset the layout after the login
        layout.getChildren().clear();

        //setup basic layout including points
        GridPane grid = new GridPane();
        Label label = new Label("Player: " + player.getName() + " has their turn: your color is " + ( blue ? "blue" : "red"));
        label.setFont(Font.font(25));
        Neighbors currentPoints = g1.getCurrentPoints();
        Label points = new Label("Points of Blue Player: " + currentPoints.Blues + " Points of Red Player: " + currentPoints.Reds);
        Label genPoints = new Label("Generations: " + g1.getNumGeneration());
        label.setTextFill(Color.RED);

        //check if over
        if (g1.isOver()) {
            label.setFont(Font.font(25));
            label.setText(g1.whoHasWon() + " HAS WON!");
        }
        // print the grid and decide the color of the pane
        for(int x = 0; x < 50; x++){
            for(int y = 0; y < 50; y++){

                //get the value
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

                    //input validation
                    if((blue && value == cellStatus.BLUE) || !blue && value == cellStatus.RED )
                        label.setText("You cant kill your own cell!");
                    else if ((blue && value == cellStatus.RED || !blue && value == cellStatus.BLUE)
                     && player.hasKilledEnemy && !((player.EnemyHit.Reds == finalX) && (player.EnemyHit.Blues == finalY)))
                        label.setText("You already killed an opponent cell");
                    else if (player.spawnedCell && value == cellStatus.DEAD)
                        label.setText("You already spawned a cell");

                    else if (!blue){
                        if(value == cellStatus.DEAD) {
                            g1.changeCellStatus(finalX, finalY, cellStatus.RED);
                            player.spawnedCell = true;
                            pane.setStyle("-fx-background-color: RED");
                        }
                        else {
                            if(player.EnemyHit.Blues != 1000) {
                                g1.changeCellStatus(finalX, finalY, cellStatus.RED);
                                player.spawnedCell = true;
                                pane.setStyle("-fx-background-color: RED");
                                player.EnemyHit = new Neighbors(1000,1000);//reset it TODO prob remove it
                            }
                            else {
                                g1.changeCellStatus(finalX, finalY, cellStatus.DEAD);
                                player.hasKilledEnemy = true;
                                player.EnemyHit = new Neighbors(finalX, finalY);
                                pane.setStyle("-fx-background-color: WHITE");
                            }
                        }
                        if(player.moveDone()) {
                            player.EnemyHit = new Neighbors(1000,1000);//reset it
                            g1.blueMove(this);
                        };

                        System.out.println("changed to red");
                        System.out.println(finalX + " " + finalY);
                    }
                    else if (blue){
                        if(value == cellStatus.DEAD) {
                            g1.changeCellStatus(finalX, finalY, cellStatus.BLUE);
                            player.spawnedCell = true;
                            pane.setStyle("-fx-background-color: BLUE");
                        }
                        else {
                            if (player.EnemyHit.Blues != 1000) { //then it is not a red cell that gets killed but a red cell being killed + blue-ed
                                g1.changeCellStatus(finalX, finalY, cellStatus.BLUE);
                                player.spawnedCell = true;
                                pane.setStyle("-fx-background-color: BLUE");
                                player.EnemyHit = new Neighbors(1000,1000);//reset it TODO prob remove
                            }
                            else {
                                g1.changeCellStatus(finalX, finalY, cellStatus.DEAD);
                                player.hasKilledEnemy = true;
                                player.EnemyHit = new Neighbors(finalX, finalY);
                                pane.setStyle("-fx-background-color: WHITE");
                            }
                        }
                        if(player.moveDone()) {
                            player.EnemyHit = new Neighbors(1000,1000);//reset it
                            g1.redMove(this);
                        }
                        System.out.println("changed to blue");
                        System.out.println(finalX + " " + finalY);
                    }

                    System.out.println(player.EnemyHit.Blues + "LOOKATTHIS" + player.EnemyHit.Reds);
                });
                grid.add(pane,x,y);
            }
        }
        grid.setGridLinesVisible(true);
        layout.getChildren().addAll(label,points, genPoints,grid);

    }

    private void login(){

        /*
         * Layout & scene for login
         * */
        //label + textfield of player 0
        Label label0 = new Label("Name of the first player:");
        TextField textField_name_0 = new TextField();

        //label + textfield of player 1
        Label label1 = new Label("Name of the second player:");
        TextField textField_name_1 = new TextField();

        //button for sending in both names
        Button loginbtn = new Button("OOKAAAY LET'SGO");

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