package uzh.gameoflife.ModelControl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import uzh.gameoflife.Cell.cellStatus;


class GameBoardTest {

    @Test
    void testGetStatus() {
        GameBoard gameBoard = GameBoard.getInstance();

        // test the starting pattern
        assertEquals(gameBoard.getStatus(2, 2), cellStatus.DEAD);
        assertEquals(gameBoard.getStatus(22, 25), cellStatus.BLUE);
        assertEquals(gameBoard.getStatus(37, 25), cellStatus.RED);
    }

    @Test
    void testChangeCellStatus() {
        GameBoard gameBoard = GameBoard.getInstance();
        gameBoard.update();

        // test change cell status on cell at (24,24)
        gameBoard.changeCellStatus(24, 24, cellStatus.RED);
        assertEquals(gameBoard.getStatus(24, 24), cellStatus.RED);
    }
}
