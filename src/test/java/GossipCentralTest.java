import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GossipCentralTest {

    @Test
    public void example1() {
        Route r1 = new Route(Arrays.asList(3, 1, 2, 3));
        Route r2 = new Route(Arrays.asList(3, 2, 3, 1));
        Route r3 = new Route(Arrays.asList(4, 2, 3, 4, 5));
        Assert.assertEquals("5", GossipCentral.driveUntilAllGossipExchanged(Arrays.asList(r1, r2, r3)));
    }

    @Test
    public void example2() {
        Route r1 = new Route(Arrays.asList(2, 1, 2));
        Route r2 = new Route(Arrays.asList(5, 2, 8));
        Assert.assertEquals(GossipCentral.DRIVERS_MISSING_GOSSIP_OUTPUT,
                GossipCentral.driveUntilAllGossipExchanged(Arrays.asList(r1, r2)));
    }

    @Test
    public void challenge1() throws IOException {
        Assert.assertEquals("5", GossipCentral.driveUntilAllGossipExchanged(
                getRoutesFromFile("src/test/resources/input1")));
    }

    @Test
    public void challenge2() throws IOException {
        Assert.assertEquals("11", GossipCentral.driveUntilAllGossipExchanged(
                getRoutesFromFile("src/test/resources/input2")));
    }

    private List<Route> getRoutesFromFile(String filePath) throws IOException {
        List<Route> routes = new ArrayList<>();

        Files.lines(Paths.get(filePath))
                .map(line -> Arrays.asList(line.split(" ")))
                .collect(Collectors.toList())
                .forEach(strList -> routes.add(new Route(convertToIntList(strList))));

        return routes;
    }

    private List<Integer> convertToIntList(List<String> strList) {
        return strList.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

}