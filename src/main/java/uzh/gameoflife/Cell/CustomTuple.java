package uzh.gameoflife.Cell;

public class CustomTuple {
    public final int Reds;
    public final int Blues;
    public final int sum;

    public CustomTuple(int reds, int blues) {
        Reds = reds;
        Blues = blues;
        sum = reds + blues;
    }
}