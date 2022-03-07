package sort.model.sorts;

import java.util.List;

/**
 *
 * @author g56212
 */
public class BubbleSort implements Sort {

    @Override
    public long runSort(int[] toSort) {
        int n = toSort.length;
        long operations = 3;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (toSort[j] > toSort[j + 1]) {
                    operations += 1;
                    // swap arr[j+1] and arr[j]
                    int temp = toSort[j];
                    operations += 1;
                    toSort[j] = toSort[j + 1];
                    operations += 1;
                    toSort[j + 1] = temp;
                    operations += 1;
                }
            }
        }
        return operations;

    }
}
