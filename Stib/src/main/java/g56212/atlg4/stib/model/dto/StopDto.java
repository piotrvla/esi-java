package g56212.atlg4.stib.model.dto;

import javafx.util.Pair;

public class StopDto extends Dto<Pair<Integer, Integer>> {

    private final int line;
    private final int station;
    private final int order;
    private final String name;

    /**
     * StopsDto constructor
     * line and station are the key for a stop
     * pair.key() = line
     * pair.value() = station
     *
     * @param line    given line
     * @param station given station
     * @param order   given order
     */
    public StopDto(int line, int station, String name, int order) {
        super(new Pair<>(line, station));
        this.line = line;
        this.station = station;
        this.name = name;
        this.order = order;

    }

    public int getLine() {
        return line;
    }

    public int getStation() {
        return station;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StopsDto{" +
                "line=" + line +
                ", station=" + station +
                ", order=" + order +
                ", name='" + name + '\'' +
                '}';
    }
}
