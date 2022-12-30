package uzh.gameoflife.Cell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class BlueCellTest {
    @Test
    public void testNextState() {
        // Create a mock CustomTuple with a sum of 3
        CustomTuple mockTuple = new CustomTuple(1,2);

        // Create a new BlueCell
        BlueCell cell = new BlueCell();

        // Call nextState with the mock CustomTuple
        cell.nextState(mockTuple);

        // Assert that the next state is BLUE
        assertEquals(cellStatus.BLUE, cell.getNextState());
    }
    @Test
    public void testNextState2() {
        // Create a mock CustomTuple with a sum of 3
        CustomTuple mockTuple = new CustomTuple(1,1);

        // Create a new BlueCell
        BlueCell cell = new BlueCell();

        // Call nextState with the mock CustomTuple
        cell.nextState(mockTuple);

        // Assert that the next state is BLUE
        assertEquals(cellStatus.BLUE, cell.getNextState());
    }

    @Test
    public void testNextStateDead() {
        // Create a mock CustomTuple with a sum of 0
        CustomTuple mockTuple = new CustomTuple(0,0) ;

        // Create a new BlueCell
        BlueCell cell = new BlueCell();

        // Call nextState with the mock CustomTuple
        cell.nextState(mockTuple);

        // Assert that the next state is DEAD
        assertEquals(cellStatus.DEAD, cell.getNextState());
    }

    @Test
    public void testGetCurrentState() {
        // Create a new BlueCell
        BlueCell cell = new BlueCell();

        // Assert that the current state is BLUE
        assertEquals(cellStatus.BLUE, cell.getCurrentState());

}}