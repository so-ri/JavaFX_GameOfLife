package uzh.gameoflife.ModelControl;
import uzh.gameoflife.Cell.CustomTuple;

public class Player implements Comparable<Player> {

    //necessary for turn handling in JavaFXMain.update(), is public because it would not matter having setters and getters here.
    public boolean hasKilledEnemy;
    public boolean spawnedCell;
    private int numFields = 12;
    public CustomTuple EnemyHit = new CustomTuple(1000,1000);
    private String name;
    public void receiveNumCells(int cells){this.numFields = cells;}
    public int getPoints() {return this.numFields;}
    public void receiveName(String name){this.name = name;}
    public String getName() {
        return name;
    }
    public boolean hasLost(){return numFields == 0;}
    public boolean moveDone() {
        return spawnedCell && hasKilledEnemy;
    }
    @Override
    public int compareTo(Player otherPlayer){
        return this.name.compareTo(otherPlayer.name);
    }

}
