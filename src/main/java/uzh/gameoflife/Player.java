package uzh.gameoflife;

public class Player implements Comparable<Player> {

    public boolean hasKilledEnemy;
    public boolean spawnedCell;
    private int numFields;
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

    public boolean getHasKilledEnemy(){ return hasKilledEnemy;}
    public void setHasKilledEnemy(boolean state){this.hasKilledEnemy = state;}

    public boolean moveDone() {
        return spawnedCell && hasKilledEnemy;
    }
    @Override
    public int compareTo(Player otherPlayer){
        return this.name.compareTo(otherPlayer.name);
    }


}
