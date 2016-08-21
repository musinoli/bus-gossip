public class BusDriver {

    private Route route;

    private int currentStop;

    private int gossip = 1;

    private boolean heardAllGossip;

    public BusDriver(Route route) {
        this.route = route;
    }

    public int getCurrentStop() {
        return currentStop;
    }

    public void drive() {
        currentStop = route.nextStop();
    }

    public int getGossip() {
        return gossip;
    }

    public void setGossip(int gossip) {
        this.gossip = gossip;
    }

    public boolean hasHeardAllGossip() {
        return heardAllGossip;
    }

    public void setHeardAllGossip(boolean heardAllGossip) {
        this.heardAllGossip = heardAllGossip;
    }
}
