package uzh.gameoflife;

public class Player {
    private short numFields;
    private String name;
    public void receiveNumCells(short cells){
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

}
