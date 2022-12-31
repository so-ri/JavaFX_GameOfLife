package uzh.gameoflife.ModelControl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uzh.gameoflife.Cell.CustomTuple;

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

    @Test
    public void testUpdateNumCells() {
        GameController.updateNumCells(5, 10);
        CustomTuple points = controller.getCurrentPoints();
        assertEquals(5, points.Reds);
        assertEquals(10, points.Blues);
    }


}
