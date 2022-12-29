package uzh.gameoflife.Cell;

public class BlueCell implements Cell {
    private cellStatus nextState;
    @Override
    public void nextState(Neighbors neighbors) {
        if(neighbors.sum < 4 && neighbors.sum > 1) nextState = cellStatus.BLUE;
        else nextState = cellStatus.DEAD;
    }

    @Override
    public cellStatus getNextState() {
        return nextState;
    }

    public cellStatus getCurrentState() {
        return cellStatus.BLUE;
    }

}
