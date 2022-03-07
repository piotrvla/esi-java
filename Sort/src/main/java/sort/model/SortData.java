package sort.model;

import sort.model.sorts.Sort;

/**
 *
 */
public class SortData {

    private String sort;
    private int time;
    private long operations;
    private int size;

    public SortData(String sort, int time, long operations, int size) {
        this.sort = sort;
        this.size = size;
        this.operations = operations;
        this.time = time;
    }

    public String getSort() {
        return sort;
    }

    public int getSize() {
        return size;
    }

    public long getOperations() {
        return operations;
    }

    public int getTime() {
        return time;
    }

}
