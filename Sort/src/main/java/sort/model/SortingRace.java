package sort.model;

import java.util.List;
import sort.model.sorts.*;
import sort.model.util.Observable;
import sort.model.util.Observer;

/**
 *
 * @author g56212
 */
public class SortingRace implements Observable {

    private int nbSorts;
    private int intreval;
    private Sort sort;
    private List<Observer> observers;

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
        runSorting();
    }

    private void runSorting() {
        
    }

    private int[] randomArray() {

        return new int[5];
    }

    @Override
    public void notifyObs(String note) {
        for (Observer observer : observers) {
            observer.update(note);
        }
    }

    @Override
    public void subscribe(Observer obs) {
        this.observers.add(obs);
    }

    @Override
    public void unsubscribe(Observer obs) {
        this.observers.remove(obs);
    }

}
