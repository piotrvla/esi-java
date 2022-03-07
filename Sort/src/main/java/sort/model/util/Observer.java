package sort.model.util;

import sort.model.sorts.Sort;

/**
 *
 * @author g56212
 */
public interface Observer {

    public void update(Sort sort, int time, long operations, int size);

}
