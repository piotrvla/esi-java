/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort.model;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MILLIS;
import java.util.ArrayList;
import java.util.List;
import sort.model.sorts.*;
import sort.model.util.Observable;
import sort.model.util.Observer;

/**
 *
 * @author g56212
 */
public class SortThread extends Thread implements Observable {

    private Sort sort;
    private SortingRace sortRace;
    private List<Observer> observers = new ArrayList<>();

    SortThread(Sort sort, SortingRace sortRace) {
        this.sort = sort;
        this.sortRace = sortRace;
        subscribe(sortRace);
    }

    @Override
    public void run() {
        int[] toSort = sortRace.makeArray();
        while (toSort != null) {
            LocalTime start = LocalTime.now();
            long operations = sort.runSort(toSort);
            LocalTime end = LocalTime.now();
            int time = (int) MILLIS.between(start, end);
            notifyObs(sort, time, operations, toSort.length);
            toSort = sortRace.makeArray();
        }
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

}
