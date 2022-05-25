package g56212.atlg4.stibride.model.dijkstra;

import g56212.atlg4.stibride.model.dto.StationDto;

import java.util.*;

/**
 * source: https://www.baeldung.com/java-dijkstra
 */
public class Node {
    private StationDto station;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(StationDto station) {
        this.station = station;
    }

    public StationDto getStation() {
        return station;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return station.equals(node.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(station);
    }

    @Override
    public String toString() {
        return "Node{" +
                "station=" + station +
                '}';
    }
}
