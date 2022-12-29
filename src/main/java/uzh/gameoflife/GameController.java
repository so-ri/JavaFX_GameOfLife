package uzh.gameoflife;

import uzh.gameoflife.Cell.cellStatus;

import java.util.Arrays;


public class GameController {
    private static Player[] players = new Player[2];
    public GameBoard board; //TODO PRIVATE THIS SHIT
    private static GameController uniqueInstance;

    public static void updateNumCells(short blues, short reds){
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

    public void startGame(){            //gameflow
        byte winner;
        byte starter = 0;
        byte other = 1;

        //decide starting player
        String[] names = {players[0].getName(), players[0].getName()};
        Arrays.sort(names);
        if (names[0] == players[1].getName()) {starter = 1; other = 0;}

        //Iterations
        while(true) {
            board.playerMove(players[starter]);
            board.update();
            //board.nextGeneration();
            if(players[starter].hasWon()) {winner = starter; break;}

            board.playerMove(players[other]);
            board.update();
            //board.nextGeneration();
            if(players[other].hasWon()) {winner = other; break;}
        }

        //done msg

    }

    public cellStatus getStatus(short x, short y){return board.getStatus(x,y);}

    public void changeCellStatus(short x, short y, cellStatus cellStatus){
        board.nextGeneration();
    }

    public void login(String name1, String name2){
        players[0].receiveName(name1);
        players[1].receiveName(name2);
        this.startGame();
    }

}
