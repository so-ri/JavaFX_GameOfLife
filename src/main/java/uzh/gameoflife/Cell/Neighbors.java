package uzh.gameoflife.Cell;

public class Neighbors {
    public final int Reds;
    public final int Blues;
    public final int sum;

    public Neighbors(int reds, int blues) {
        Reds = reds;
        Blues = blues;
        sum = reds + blues;
    }
}