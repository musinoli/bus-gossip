import java.util.List;
import java.util.stream.Collectors;

/**
 * https://dzone.com/articles/java-code-challenge-bus-gossip
 */
public final class GossipCentral {
    private static int remainingMins = 48; // TODO: set to 480;

    private GossipCentral() {}

    public static void gossip(List<Route> routes) {

        List<BusDriver> drivers = getDrivers(routes);

        while (remainingMins-- > 0) {
            drivers.forEach(BusDriver::drive);

            for (List<BusDriver> driversAtSameStop : groupedDriversAtSameStop(drivers)) {
                int totalGossip = driversAtSameStop.stream()
                        .mapToInt(BusDriver::getGossip).sum();

                driversAtSameStop.forEach(driver -> driver.setGossip(totalGossip));
            }

            drivers.forEach(driver -> System.err.printf("Driver %d numOfGossip: %d\n",
                    drivers.indexOf(driver), driver.getGossip()));
            System.err.println("################################");
        }
    }

    private static List<List<BusDriver>> groupedDriversAtSameStop(List<BusDriver> drivers) {
        return drivers.stream()
                .collect(Collectors.groupingBy(BusDriver::getCurrentStop))
                .values().stream()
                .filter(driversAtSameStop -> driversAtSameStop.size() > 1).collect(Collectors.toList());
    }

    private static List<BusDriver> getDrivers(List<Route> routes) {
        return routes.stream().map(BusDriver::new).collect(Collectors.toList());
    }
}
