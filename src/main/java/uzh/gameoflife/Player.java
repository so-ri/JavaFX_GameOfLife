package uzh.gameoflife;

import uzh.gameoflife.Cell.Neighbors;

public class Player implements Comparable<Player> {
    public boolean hasKilledEnemy;
    public boolean spawnedCell;
    private int numFields = 12;

    public Neighbors EnemyHit = new Neighbors(1000,1000); //no neighbors here - just too lazy to rename the 2 int tuple object helper
    private String name;
    public void receiveNumCells(int cells){this.numFields = cells;}

    public void receiveName(String name){
        this.name = name;
        System.out.println(name);
    }
    public boolean hasLost(){return numFields == 0;}
    public String getName() {
        return name;
    }
    public int getPoints() {return this.numFields;}

    public boolean moveDone() {
        return spawnedCell && hasKilledEnemy;
    }
    @Override
    public int compareTo(Player otherPlayer){
        return this.name.compareTo(otherPlayer.name);
    }

}
