package uzh.gameoflife.ModelControl;

import uzh.gameoflife.Cell.CustomTuple;
import uzh.gameoflife.Cell.cellStatus;
import uzh.gameoflife.JavaFXMain;

import java.util.Arrays;

public class GameController {
    private static final Player[] players = new Player[2];
    private final GameBoard board;
    private static GameController uniqueInstance;

    private GameController(){ //Singleton Constructor
        players[0] = new Player();
        players[1] = new Player();
        board = new GameBoard();
    }
    public static synchronized GameController getInstance(){
        if (uniqueInstance == null) uniqueInstance = new GameController();
        return uniqueInstance;
    }

    public void login(String name1, String name2, JavaFXMain helloApplication){
        players[0].receiveName(name1);
        players[1].receiveName(name2);
        this.startGame(helloApplication);
    }

    public void startGame(JavaFXMain GUI){
        Arrays.sort(players);                        //decide starting player
        GUI.updateGrid(true, players[0]);       //entry point
    }

    //after a Turn of the blue player, the other color's Move is being called. splitting it up into two functions helps with giving over less variables.
    public void blueMove(JavaFXMain GUI){
        board.update();
        board.nextGeneration();
        GUI.updateGrid(true, players[0]);
    }
    public void redMove(JavaFXMain GUI) {
        board.update();
        board.nextGeneration();
        GUI.updateGrid(false, players[1]);
    }
    public static void updateNumCells(int blues, int reds){
        players[0].receiveNumCells(blues);
        players[1].receiveNumCells(reds);
    }

    public String whoHasWon() {return players[0].hasLost() ? players[1].getName() : players[0].getName();} //if 0 has Lost: return Name of Player 1. else: return Name of Player 1
    public boolean isOver(){return (players[0].hasLost() || players[1].hasLost());}
    public cellStatus getStatus(int x, int y){return board.getStatus(x,y);}
    public void changeCellStatus(int x, int y, cellStatus cellStatus){
        board.changeCellStatus(x,y,cellStatus);
    }
    public CustomTuple getCurrentPoints() {return new CustomTuple(players[0].getPoints(), players[1].getPoints());}
    public int getNumGeneration() {return board.getNumGeneration();}
}
