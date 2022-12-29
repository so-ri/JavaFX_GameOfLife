package uzh.gameoflife.Cell;

public class DeadCell implements Cell {
    private cellStatus nextState;
    @Override
    public void nextState(Neighbors neighbors) {

    }

    @Override
    public cellStatus getNextState() {
        return nextState;
    }

    public cellStatus getCurrentState() {
        return cellStatus.DEAD;
    }
}
