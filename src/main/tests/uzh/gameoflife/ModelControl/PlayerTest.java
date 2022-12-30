package uzh.gameoflife.ModelControl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void testHasLost() {
        Player player = new Player();

        // Test 1: player has 0 fields
        player.receiveNumCells(0);
        assertTrue(player.hasLost());

        // Test 2: player has more than 0 fields
        player.receiveNumCells(1);
        assertFalse(player.hasLost());

        // Test 3: player has negative number of fields
        player.receiveNumCells(-1);
        assertFalse(player.hasLost());
    }

    @Test
    public void testMoveDone() {
        Player player = new Player();

        // Test 1: player has not killed enemy or spawned cell
        assertFalse(player.moveDone());

        // Test 2: player has killed enemy but not spawned cell
        player.hasKilledEnemy = true;
        assertFalse(player.moveDone());

        // Test 3: player has spawned cell but not killed enemy
        player.hasKilledEnemy = false;
        player.spawnedCell = true;
        assertFalse(player.moveDone());

        // Test 4: player has killed enemy and spawned cell
        player.hasKilledEnemy = true;
        assertTrue(player.moveDone());
    }

    @Test
    public void testCompareTo() {
        Player player1 = new Player();
        player1.receiveName("Alice");
        Player player2 = new Player();
        player2.receiveName("Bob");
        Player player3 = new Player();
        player3.receiveName("Charlie");

        // Test 1: compare player1 to player2
        assertTrue(player1.compareTo(player2) < 0);
        assertTrue(player2.compareTo(player1) > 0);

        // Test 2: compare player2 to player3
        assertTrue(player2.compareTo(player3) < 0);
        assertTrue(player3.compareTo(player2) > 0);

        // Test 3: compare player1 to player3
        assertTrue(player1.compareTo(player3) < 0);
        assertTrue(player3.compareTo(player1) > 0);

        // Test 4: compare player1 to itself
        assertTrue(player1.compareTo(player1) == 0);
    }
}