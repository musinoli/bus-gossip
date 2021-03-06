import java.util.List;

public class Route {

    private List<Integer> stops;

    private int stopCounter;

    private int numOfStops;

    public Route(List<Integer> stops) {
        this.stops = stops;
        numOfStops = stops.size();
    }

    public int nextStop() {
        return stops.get(stopCounter++ % numOfStops);
    }
}
