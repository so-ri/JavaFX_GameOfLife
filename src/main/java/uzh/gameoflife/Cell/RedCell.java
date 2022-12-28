package uzh.gameoflife.Cell;

public class RedCell implements Cell {
    private cellStatus nextState;
    @Override
    public void nextState(Neighbors neighbors) {

    }

    @Override
    public cellStatus getNextState() {
        return nextState;
    }
}
