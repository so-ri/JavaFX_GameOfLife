package uzh.gameoflife.Cell;

public class DeadCell implements Cell {
    private cellStatus nextState;

    //chooses next state according to game rules
    @Override
    public void nextState(CustomTuple customTuple) {
        if((customTuple.sum) == 3 && customTuple.Blues >= 2) nextState = cellStatus.BLUE;
        else if(customTuple.sum == 3) nextState = cellStatus.RED;
        else nextState = cellStatus.DEAD;
    }

    @Override
    public cellStatus getNextState() {
        return nextState;
    }

    public cellStatus getCurrentState() {
        return cellStatus.DEAD;
    }
}
