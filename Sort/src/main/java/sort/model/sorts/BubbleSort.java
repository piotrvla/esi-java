package sort.model.sorts;

import java.util.List;

/**
 *
 * @author g56212
 */
public class BubbleSort implements Sort {

    @Override
    public int runSort(int[] toSort) {
        int n = toSort.length;
        int temp = 0;
        int operations = 2;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (toSort[j - 1] > toSort[j]) {
                    temp = toSort[j - 1];
                    toSort[j - 1] = toSort[j];
                    toSort[j] = temp;
                    operations += 4;
                }

            }
        }
        return operations;

    }
}
