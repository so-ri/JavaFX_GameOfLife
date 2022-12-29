package uzh.gameoflife.Cell;


public interface Cell {

    void nextState(Neighbors neighbors);
    cellStatus getNextState();
    cellStatus getCurrentState();
}
