package uzh.gameoflife.Cell;

public class RedCell implements Cell {
    private cellStatus nextState;

    //chooses next state according to game rules
    @Override
    public void nextState(CustomTuple customTuple) {
        if(customTuple.sum < 4 && customTuple.sum > 1) nextState = cellStatus.RED;
        else nextState = cellStatus.DEAD;
    }

    @Override
    public cellStatus getNextState() {
        return nextState;
    }

    public cellStatus getCurrentState() {
        return cellStatus.RED;
    }
}
