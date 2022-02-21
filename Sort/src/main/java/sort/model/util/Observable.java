package sort.model.util;

/**
 *
 * @author g56212
 */
public interface Observable {

    public void notifyObs(String note);

    public void subscribe(Observer obs);

    public void unsubscribe(Observer obs);
}
