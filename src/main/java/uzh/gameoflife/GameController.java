package uzh.gameoflife;

import uzh.gameoflife.Cell.cellStatus;

import java.util.Arrays;


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
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GameController {
    private static Player[] players = new Player[2];
    public GameBoard board; //TODO PRIVATE THIS SHIT
    private static GameController uniqueInstance;

    public static void updateNumCells(int blues, int reds){
        players[0].receiveNumCells(blues);
        players[1].receiveNumCells(reds);
    }

    private GameController(){
        players[0] = new Player();
        players[1] = new Player();
        board = new GameBoard();
    }

    public static synchronized GameController getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new GameController();
        }
        return uniqueInstance;
    }

    public void startGame(HelloApplication GUI){            //gameflow

        byte winner;


        //decide starting player

        Arrays.sort(players);


        //Iterations


        GUI.updateGrid(true, players[0]);


            /*players[starter].hasKilledEnemy.
            board.update();
            if (players[starter].hasWon()) {
                winner = starter;
            }
            GUI.updateGrid(false, players[other]);
            System.out.println("siuuu");
            board.update();
            //board.nextGeneration();
            if (players[other].hasWon()) {
                winner = other;
            }
        }*/

        //done msg

    }
    public void blueMove(HelloApplication GUI){
        board.update();
        board.nextGeneration();
        GUI.updateGrid(true, players[0]);
    }
    public void redMove(HelloApplication GUI) {
        board.update();
        board.nextGeneration();
        GUI.updateGrid(false, players[1]);
    }
    public cellStatus getStatus(int x, int y){return board.getStatus(x,y);}

    public void changeCellStatus(int x, int y, cellStatus cellStatus){
        board.changeCellStatus(x,y,cellStatus);
    }

    public void login(String name1, String name2, HelloApplication helloApplication){
        players[0].receiveName(name1);
        players[1].receiveName(name2);
        this.startGame(helloApplication);

    }

}
