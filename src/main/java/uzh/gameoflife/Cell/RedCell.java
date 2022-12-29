package uzh.gameoflife.Cell;

public class RedCell implements Cell {
    private cellStatus nextState = cellStatus.RED;
    @Override
    public void nextState(Neighbors neighbors) {

    }

    @Override
    public cellStatus getNextState() {
        return nextState;
    }

    public cellStatus getCurrentState() {
        return cellStatus.RED;
    }
}
