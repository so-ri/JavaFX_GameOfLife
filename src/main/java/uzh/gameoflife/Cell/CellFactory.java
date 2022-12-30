package uzh.gameoflife.Cell;

public class CellFactory {
    public static Cell getCell(cellStatus cellStatus) {
        return switch (cellStatus) {
            case RED -> new RedCell();
            case BLUE -> new BlueCell();
            default -> new DeadCell();
        };
    }
}