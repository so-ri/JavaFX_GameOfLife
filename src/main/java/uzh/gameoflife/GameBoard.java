package uzh.gameoflife;

import uzh.gameoflife.Cell.*;

import java.util.Arrays;

public class GameBoard {
    private short numGeneration = 0;
    private Cell[][] board = new Cell[50][50];
    private Player[] players = new Player[2];

    public void initializeBoard() {        //fills 2 dimensional Array with DeadCells and a starting Pattern
        for (Cell[] cellArray : board) {
            Arrays.fill(cellArray, CellFactory.getCell(cellStatus.DEAD));
        }
        board[15][24] = CellFactory.getCell(cellStatus.BLUE);
        board[35][24] = CellFactory.getCell(cellStatus.RED);
    }

    public void initializePlayers(){
        players[0] = new Player();
        players[1] = new Player();
    }

    public void playerMove(Player player){
        //TODO GUI INPUT
    }

    public void update() {      //sets the next state of the cells according to game rules but doesn't apply them yet
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                board[i][j].nextState(getNext(i,j));
            }
        }

    }

    public void nextGeneration() {      //generates new generation of cells (with updated states)
        short numBlue = 0;
        short numRed = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (board[i][j].getNextState() == cellStatus.BLUE) numBlue += 1;
                if (board[i][j].getNextState() == cellStatus.BLUE)  numRed += 1;
                board[i][j] = CellFactory.getCell(board[i][j].getNextState());
            }
        }
        numGeneration += 1;
        GameController.updateNumCells(numBlue,numRed);
    }

    private Neighbors getNext(int i, int j) {       //gets neighboring colors of a cell

        short RedCounter = 0;
        short BlueCounter = 0;

        for (int x = i-1; x <= i+1; x++) {          //iterates through neighboring x-values
            if(x < 0 || x >= 50) continue;         //if a value is out of bounds (edge of map) then skip
            for (int y = j-1; y <= j+1; y++) {      //iterates through neighboring y-values
                if(y < 0 || y >= 50) continue;     //if a value is out of bounds (edge of map) then skip

                if(board[x][y].getClass() == BlueCell.class) BlueCounter += 1;
                if(board[x][y].getClass() == RedCell.class) RedCounter += 1;
            }
        }

        return new Neighbors(RedCounter, BlueCounter);
    }



}
