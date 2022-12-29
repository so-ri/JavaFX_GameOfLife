package uzh.gameoflife.Cell;

public class DeadCell implements Cell {
    private cellStatus nextState;
    @Override
    public void nextState(Neighbors neighbors) {
        if((neighbors.sum) == 3 && neighbors.Blues >= 2) nextState = cellStatus.BLUE;
        else if(neighbors.sum == 3) nextState = cellStatus.RED;
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
