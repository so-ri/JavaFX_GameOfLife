package uzh.gameoflife;

import uzh.gameoflife.Cell.Neighbors;

public class Player implements Comparable<Player> {

    public boolean hasKilledEnemy;
    public boolean spawnedCell;
    private int numFields;

    public Neighbors EnemyHit; //no neighbors here - just too lazy to rename the 2 int tuple
    private String name;
    public void receiveNumCells(int cells){
        this.numFields = cells;
    }

    public void receiveName(String name){
        this.name = name;
        System.out.println(name);
    }

    public boolean hasWon(){
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean moveDone() {
        return spawnedCell && hasKilledEnemy;
    }
    @Override
    public int compareTo(Player otherPlayer){
        return this.name.compareTo(otherPlayer.name);
    }


}
