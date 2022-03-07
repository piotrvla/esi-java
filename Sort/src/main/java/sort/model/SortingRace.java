package sort.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import sort.model.sorts.*;
import sort.model.util.Observable;
import sort.model.util.Observer;

/**
 *
 * @author g56212
 */
public class SortingRace implements Observable, Observer {

    private final static Random nbRdm = new Random();
    private String sort;
    private final int max;
    private final int jump;
    private int current;
    private int nbThreads;
    private List<Observer> observers = new ArrayList<>();

    public SortingRace(int nbThreads, Difficulty intervalSort, String sort) {
        this.max = intervalSort.getValue();
        this.jump = max / 10;
        this.current = 0;
        this.sort = sort;
        this.nbThreads = nbThreads;
    }

    public void runSorting() {
        for (int i = 0; i < nbThreads; i++) {
            Sort threadSort = switch (sort.toLowerCase()) {
                case "merge sort" ->
                    new MergeSort();
                case "bubble sort" ->
                    new BubbleSort();
                default ->
                    new MergeSort();
            };
            SortThread thread = new SortThread(threadSort, this);
            thread.start();
        }
    }

    public synchronized int[] makeArray() {
        if (current > max) {
            return null;
        }
        int[] tab = new int[current];
        for (int i = 0; i < current; i++) {
            tab[i] = nbRdm.nextInt();
        }
        current += jump;
        return tab;
    }

    @Override
    public void notifyObs(Sort sort, int time, long operations, int size) {
        for (Observer observer : observers) {
            observer.update(sort, time, operations, size);
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

    @Override
    public void update(Sort sort, int time, long operations, int size) {
        notifyObs(sort, time, operations, size);
    }

}
