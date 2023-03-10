package uzh.gameoflife.Cell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class RedCellTest {
    @Test
    public void testNextState() {
        // Create a mock CustomTuple with a sum of 3
        CustomTuple mockTuple = new CustomTuple(2,1) ;

        // Create a new RedCell
        RedCell cell = new RedCell();

        // Call nextState with the mock CustomTuple
        cell.nextState(mockTuple);

        // Assert that the next state is BLUE
        assertEquals(cellStatus.RED, cell.getNextState());
    }

    @Test
    public void testNextStateDead() {
        // Create a mock CustomTuple with a sum of 0
        CustomTuple mockTuple = new CustomTuple(0,0) ;

        // Create a new RedCell
        RedCell cell = new RedCell();

        // Call nextState with the mock CustomTuple
        cell.nextState(mockTuple);

        // Assert that the next state is DEAD
        assertEquals(cellStatus.DEAD, cell.getNextState());
    }

    @Test
    public void testGetCurrentState() {
        // Create a new RedCell
        RedCell cell = new RedCell();

        // Assert that the current state is BLUE
        assertEquals(cellStatus.RED, cell.getCurrentState());

    }}