package uzh.gameoflife.Cell;

public class BlueCell implements Cell {
    private cellStatus nextState = cellStatus.BLUE;
    @Override
    public void nextState(Neighbors neighbors) {

    }

    @Override
    public cellStatus getNextState() {
        return nextState;
    }

    public cellStatus getCurrentState() {
        return cellStatus.BLUE;
    }

}
