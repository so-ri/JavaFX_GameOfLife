package uzh.gameoflife.Cell;

public class BlueCell implements Cell {
    private cellStatus nextState;

    //chooses next state according to game rules
    @Override
    public void nextState(CustomTuple customTuple) {
        if(customTuple.sum == 3 || customTuple.sum == 2) nextState = cellStatus.BLUE;
        else nextState = cellStatus.DEAD;
    }

    @Override
    public cellStatus getNextState() {return nextState;}

    public cellStatus getCurrentState() {
        return cellStatus.BLUE;
    }

}
