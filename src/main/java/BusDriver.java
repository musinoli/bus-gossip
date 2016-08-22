import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BusDriver {
    private Route route;

    private int currentStop;

    private boolean heardAllGossip;

    private Set<Integer> gossip = new HashSet<>();

    public BusDriver(int gossipId, Route route) {
        this.route = route;
        gossip.add(gossipId);
    }

    public int getCurrentStop() {
        return currentStop;
    }

    public void drive() {
        currentStop = route.nextStop();
    }

    public boolean hasHeardAllGossip() {
        return heardAllGossip;
    }

    public void isUpToDateWithGossip() {
        heardAllGossip = true;
    }

    public Set<Integer> getGossip() {
        return gossip;
    }

    public void addGossip(Collection<Integer> someGossip) {
        this.getGossip().addAll(someGossip);
    }
}
