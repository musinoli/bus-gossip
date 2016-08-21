import java.util.List;
import java.util.stream.Collectors;

/**
 * https://dzone.com/articles/java-code-challenge-bus-gossip
 */
public final class GossipCentral {
    private static final int MAX_NUM_OF_STOPS = 480;

    public static final String DRIVERS_MISSING_GOSSIP_OUTPUT = "never";

    private GossipCentral() {}

    public static String driveUntilAllGossipExchanged(List<Route> routes) {

        List<BusDriver> drivers = getDrivers(routes);
        int allGossip = drivers.size();

        for (int stopCounter = 1; stopCounter <= MAX_NUM_OF_STOPS; stopCounter++ ) {
            drivers.forEach(BusDriver::drive);

            for (List<BusDriver> driversAtSameStop : groupedDriversAtSameStop(drivers)) {
                for (BusDriver driver : driversAtSameStop) {
                    driver.setGossip(totalGossip(driversAtSameStop));
                    if (driver.getGossip() >= allGossip) {
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

    private static int totalGossip(List<BusDriver> drivers) {
        return drivers.stream().mapToInt(BusDriver::getGossip).sum();
    }

    private static List<List<BusDriver>> groupedDriversAtSameStop(List<BusDriver> drivers) {
        return drivers.stream()
                .collect(Collectors.groupingBy(BusDriver::getCurrentStop))
                .values().stream()
                .filter(driversAtSameStop -> driversAtSameStop.size() > 1)
                .collect(Collectors.toList());
    }

    private static List<BusDriver> getDrivers(List<Route> routes) {
        return routes.stream().map(BusDriver::new).collect(Collectors.toList());
    }
}
