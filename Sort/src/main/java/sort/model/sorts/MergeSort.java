package sort.model.sorts;

/**
 *
 * @author g56212
 */
public class MergeSort implements Sort {

    @Override
    public long runSort(int[] toSort) {
        int n = toSort.length;

        return fusionSort(toSort, n);

    }

    private int fusionSort(int[] arr, int n) {
        int count = 0;
        if (n < 2) {//une
            count++;
            return count;
        }
        int mid = n / 2;//une
        int[] l = new int[mid];//une
        int[] r = new int[n - mid];//une
        count += 3;

        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];//une
            count++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = arr[i];//une
            count++;
        }

        count += fusionSort(l, mid);

        count += fusionSort(r, n - mid);

        count += merge(arr, l, r, mid, n - mid);
        return count;
    }

    private long merge(int[] a, int[] l, int[] r, int left, int right) {
        int count = 0;
        int i = 0;//une
        int j = 0;//une
        int k = 0;//une
        count += 4;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {//une
                a[k++] = l[i++];//une
                count += 2;
            } else {
                a[k++] = r[j++];//une
                count++;
            }
        }
        while (i < left) {
            a[k++] = l[i++];//une
            count++;
        }
        while (j < right) {
            a[k++] = r[j++];//une
            count++;
        }
        return count;
    }
}
