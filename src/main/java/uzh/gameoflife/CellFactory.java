package uzh.gameoflife;

import uzh.gameoflife.Cell.*;

class CellFactory {
    public static Cell getCell(cellStatus cellStatus) {
        switch(cellStatus) {
            case RED: return new RedCell();
            case BLUE: return new BlueCell();
            default: return new DeadCell();
        }
    }
}
