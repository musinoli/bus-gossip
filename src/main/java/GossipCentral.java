import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://dzone.com/articles/java-code-challenge-bus-gossip
 */
public final class GossipCentral {
    private static final int MAX_NUM_OF_STOPS = 480;

    private static int gossipIdCounter = 1;

    public static final String DRIVERS_MISSING_GOSSIP_OUTPUT = "never";

    private GossipCentral() {}

    public static String driveUntilAllGossipExchanged(List<Route> routes) {

        List<BusDriver> drivers = getDrivers(routes);
        int totalGossip = drivers.size();

        for (int stopCounter = 1; stopCounter <= MAX_NUM_OF_STOPS; stopCounter++) {
            drivers.forEach(BusDriver::drive);

            for (List<BusDriver> driversAtSameStop : groupedDriversAtSameStop(drivers)) {
                for (BusDriver driver : driversAtSameStop) {
                    driver.addGossip(allGossip(driversAtSameStop));
                    if (!driver.hasHeardAllGossip() && driver.getGossip().size() == totalGossip) {
                        driver.isUpToDateWithGossip();
                    }
                }
            }

            if (drivers.stream().allMatch(BusDriver::hasHeardAllGossip)) {
                return String.valueOf(stopCounter);
            }
        }

        return DRIVERS_MISSING_GOSSIP_OUTPUT;
    }

    private static Collection<Integer> allGossip(List<BusDriver> drivers) {
        return drivers.stream().map(BusDriver::getGossip)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<List<BusDriver>> groupedDriversAtSameStop(List<BusDriver> drivers) {
        return drivers.stream()
                .collect(Collectors.groupingBy(BusDriver::getCurrentStop))
                .values().stream()
                .filter(driversAtSameStop -> driversAtSameStop.size() > 1)
                .collect(Collectors.toList());
    }

    private static List<BusDriver> getDrivers(List<Route> routes) {
        return routes.stream()
                .map(route -> new BusDriver(gossipIdCounter++, route))
                .collect(Collectors.toList());
    }
}
