package g56212.atlg4.stibride.model.dto;

import java.util.Objects;

public class StationDto extends Dto<Integer> {
    private final String name;

    /**
     * Dto constructor with the key of the data.
     *
     * @param station
     * @param name
     */
    public StationDto(int station, String name) {
        super(station);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StationDto that = (StationDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return name.toUpperCase();
    }
}
