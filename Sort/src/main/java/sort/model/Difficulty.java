package sort.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author g56212
 */
public enum Difficulty {
    EASY(1000),
    HARD(10000),
    EXTREME(100000);

    private int sorts;

    Difficulty(int sorts) {
        this.sorts = sorts;
    }
    public int getValue() {
        return this.sorts;
    }    
    public static List<Difficulty> get() {
        return Arrays.asList(EASY, HARD, EXTREME);
    }

}
