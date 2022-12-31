package uzh.gameoflife.ModelControl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void testHasLost() {
        Player player = new Player();

        // player has 0 fields
        player.receiveNumCells(0);
        assertTrue(player.hasLost());

        // player has more than 0 fields
        player.receiveNumCells(20);
        assertFalse(player.hasLost());

    }

    @Test
    public void testMoveDone() {
        Player player = new Player();

        // Test that player has not killed enemy or spawned cell
        assertFalse(player.moveDone());

        // Test that player has killed enemy but not spawned cell
        player.hasKilledEnemy = true;
        assertFalse(player.moveDone());

        // Test that player has spawned cell but not killed enemy
        player.hasKilledEnemy = false;
        player.spawnedCell = true;
        assertFalse(player.moveDone());

        // Test that player has killed enemy and spawned cell
        player.hasKilledEnemy = true;
        assertTrue(player.moveDone());
    }

    @Test
    public void testCompareTo() {
        //create 2 players
        Player player1 = new Player();
        player1.receiveName("bbb");
        Player player2 = new Player();
        player2.receiveName("aaa");

        //test whether they are sorted alphabetically
        Player[] players = {player1, player2};
        Arrays.sort(players);
        assertSame("aaa", players[0].getName());
    }
}