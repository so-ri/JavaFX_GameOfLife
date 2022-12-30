package uzh.gameoflife.ModelControl;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uzh.gameoflife.Cell.CellFactory;
import uzh.gameoflife.Cell.CustomTuple;
import uzh.gameoflife.Cell.RedCell;
import uzh.gameoflife.Cell.cellStatus;
import uzh.gameoflife.ModelControl.GameBoard;

class GameBoardTest {

    /*
    @Test
    void testGetNeighbors() {
        GameBoard gameBoard = new GameBoard();
        // test neighbors of cell at (0,0)
        CustomTuple neighbors = gameBoard.getNeighbors(0, 0);
        assertEquals(0, neighbors.Blues);
        assertEquals(0, neighbors.Reds);
        // test neighbors of cell at (24,24)
        neighbors = gameBoard.getNeighbors(24, 24);
        assertEquals(2, neighbors.Blues);
        assertEquals(0, neighbors.Reds);
        // test neighbors of cell at (25,24)
        neighbors = gameBoard.getNeighbors(25, 24);
        assertEquals(1, neighbors.Blues);
        assertEquals(0, neighbors.Reds);
        // test neighbors of cell at (26,24)
        neighbors = gameBoard.getNeighbors(26, 24);
        assertEquals(1, neighbors.Blues);
        assertEquals(0, neighbors.Reds);
    }
    */

    /*
    @Test
    void testUpdate() {
        Player tester = new Player();
        GameController testcontroller = GameController.getInstance();
        GameBoard gameBoard = new GameBoard();
        gameBoard.changeCellStatus(5,5, cellStatus.BLUE);
        // test update on cell at (24,24)
        gameBoard.update();

        assertEquals(CellFactory.getCell(gameBoard.getStatus(5, 5)).getNextState(), cellStatus.DEAD);
    }



    @Test
    void testNextGeneration() {
        GameBoard gameBoard = new GameBoard();
        // test next generation on cell at (24,24)
        gameBoard.nextGeneration();
        assertEquals(CellFactory.getCell(gameBoard.getStatus(24, 24)).getCurrentState(), cellStatus.BLUE);
        // test next generation on cell at (25,24)
        gameBoard.nextGeneration();
        assertEquals(CellFactory.getCell(gameBoard.getStatus(25, 24)).getCurrentState(), cellStatus.BLUE);
        // test next generation on cell at (26,24)
        gameBoard.nextGeneration();
        assertEquals(CellFactory.getCell(gameBoard.getStatus(26, 24)).getCurrentState(), cellStatus.BLUE);
    }
     */
    @Test
    void testGetStatus() {
        GameBoard gameBoard = new GameBoard();
        // test the starting pattern
        assertEquals(gameBoard.getStatus(24, 24), cellStatus.DEAD);
        assertEquals(gameBoard.getStatus(22, 25), cellStatus.BLUE);
        assertEquals(gameBoard.getStatus(37, 25), cellStatus.RED);
    }

    @Test
    void testChangeCellStatus() {
        GameBoard gameBoard = new GameBoard();
        gameBoard.update();
        // test change cell status on cell at (24,24)
        gameBoard.changeCellStatus(24, 24, cellStatus.RED);
        assertEquals(gameBoard.getStatus(24, 24), cellStatus.RED);
        // test change cell status on cell at (25,24)
        gameBoard.changeCellStatus(25, 24, cellStatus.RED);
        assertEquals(gameBoard.getStatus(25, 24), cellStatus.RED);
        // test change cell status on cell at (26,24)
        gameBoard.changeCellStatus(26, 24, cellStatus.RED);
        assertEquals(gameBoard.getStatus(26, 24), cellStatus.RED);
    }

    /*
    @Test
    void testGetNumGeneration() {
        GameBoard gameBoard = new GameBoard();
        // test getNumGeneration after one generation
        gameBoard.nextGeneration();
        assertEquals(gameBoard.getNumGeneration(), 1);
        // test getNumGeneration after two generations
        gameBoard.nextGeneration();
        assertEquals(gameBoard.getNumGeneration(), 2);
        // test getNumGeneration after three generations
        gameBoard.nextGeneration();
        assertEquals(gameBoard.getNumGeneration(), 3);
    }
     */


}
