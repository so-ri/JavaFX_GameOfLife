package uzh.gameoflife.Cell;


public interface Cell {

    void nextState(CustomTuple customTuple);
    cellStatus getNextState();
    cellStatus getCurrentState();
}
