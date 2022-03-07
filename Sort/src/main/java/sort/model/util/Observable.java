package sort.model.util;

import sort.model.sorts.Sort;

/**
 *
 * @author g56212
 */
public interface Observable {

    public void notifyObs(Sort sort, int time, long operations, int size);

    public void subscribe(Observer obs);

    public void unsubscribe(Observer obs);
}
