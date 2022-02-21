package sort.model;

import sort.model.sorts.*;
import sort.model.util.Observable;

/**
 *
 * @author g56212
 */
public class SortingRace implements Observable{

    private int nbSorts;
    private int intreval;
    private Sort sort;

    public SortingRace(int nbSorts, Difficulty intervalSort, String sort) {
        switch (sort.toLowerCase()) {
            case "merge sort":
                this.sort = new MergeSort();
                break;
            case "bubble sort":
                this.sort = new BubbleSort();
                break;
        }
        this.nbSorts = nbSorts;
        this.intreval = intervalSort.getValue();
    }

    private int[] randomArray() {

        return new int[5];
    }

}
