package uzh.gameoflife.ModelControl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    private GameController controller;

    @BeforeEach
    public void setUp() {
        controller = GameController.getInstance();
    }

    @AfterEach
    public void tearDown() {
        controller = null;
    }

    @Test
    public void testSingleton() {
        GameController controller2 = GameController.getInstance();
        assertSame(controller, controller2);
    }

    /*
    @Test
    public void testLogin() {
        controller.login("player1", "player2", null);
        assertEquals("player1", controller.players[0].getName());
        assertEquals("player2", controller.players[1].getName());
    }

    @Test
    public void testStartGame() {
        controller.startGame(null);
    }

    @Test
    public void testBlueMove() {
        controller.blueMove(null);
    }

    @Test
    public void testRedMove() {
        controller.redMove(null);
    }

    @Test
    public void testUpdateNumCells() {
        controller.updateNumCells(5, 10);
        assertEquals(5, controller.players[0].getPoints());
        assertEquals(10, controller.players[1].getPoints());
    }

    @Test
    public void testWhoHasWon() {
        controller.players[0].receiveNumCells(5);
        controller.players[1].receiveNumCells(10);
        assertEquals("player2", controller.whoHasWon());
    }

    @Test
    public void testIsOver() {
        controller.players[0].receiveNumCells(5);
        assertFalse(controller.isOver());
        controller.players[0].receiveNumCells(0);
        assertTrue(controller.isOver());
    }

    @Test
    public void testGetStatus() {
        controller.board.grid[0][0] = cellStatus.BLUE;
        assertEquals(cellStatus.BLUE, controller.getStatus(0, 0));
    }

    @Test
    public void testChangeCellStatus() {
        controller.changeCellStatus(0, 0, cellStatus.RED);
        assertEquals(cellStatus.RED, controller.board.grid[0][0]);
    }

    @Test
    public void testGetCurrentPoints() {
        controller.players[0].receiveNumCells(5);
        controller.players[1].receiveNumCells(10);
        CustomTuple points = controller.getCurrentPoints();
        assertEquals(5, points.x);
        assertEquals(10, points.y);
    }

    @Test
    public void testGetNumGeneration() {
        assertEquals(0, controller.getNumGeneration());
    }

     */

}
