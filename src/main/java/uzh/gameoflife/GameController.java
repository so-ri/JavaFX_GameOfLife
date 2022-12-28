package uzh.gameoflife;

import java.util.Arrays;


public class GameController {
    private static Player[] players = new Player[2];
    private GameBoard board;
    private static GameController uniqueInstance;

    public static void updateNumCells(short blues, short reds){
        players[0].receiveNumCells(blues);
        players[1].receiveNumCells(reds);
    }

    private GameController(){
        players[0] = new Player();
        players[1] = new Player();

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
        board.initializeBoard();

        //decide starting player
        String[] names = {players[0].getName(), players[0].getName()};
        Arrays.sort(names);
        if (names[0] == players[1].getName()) {starter = 1; other = 0;}

        //Iterations
        while(true) {
            board.playerMove(players[starter]);
            board.update();
            board.nextGeneration();
            if(players[starter].hasWon()) {winner = starter; break;}

            board.playerMove(players[other]);
            board.update();
            board.nextGeneration();
            if(players[other].hasWon()) {winner = other; break;}
        }

        //done msg

    }

    public void receiveName0(String name) {players[0].receiveName(name);}
    public void receiveName1(String name) {players[1].receiveName(name);}

}
