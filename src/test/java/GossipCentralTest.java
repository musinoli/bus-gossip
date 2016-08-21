import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class GossipCentralTest {

    @Test
    public void sample1() {
        Route r1 = new Route(Arrays.asList(3, 1, 2, 3));
        Route r2 = new Route(Arrays.asList(3, 2, 3, 1));
        Route r3 = new Route(Arrays.asList(4, 2, 3, 4, 5));
        Assert.assertEquals("5", GossipCentral.driveUntilAllGossipExchanged(Arrays.asList(r1, r2, r3)));
    }

    @Test
    public void multipleSharedStops() {
        Route r1 = new Route(Arrays.asList(3, 1, 2, 3));
        Route r2 = new Route(Arrays.asList(3, 2, 3, 1));
        Route r3 = new Route(Arrays.asList(4, 2, 3, 4, 5));
        Route r4 = new Route(Arrays.asList(4, 2, 3, 4, 5));
        Assert.assertEquals("5", GossipCentral.driveUntilAllGossipExchanged(Arrays.asList(r1, r2, r3, r4)));
    }

    @Test
    public void sample2() {
        Route r1 = new Route(Arrays.asList(2, 1, 2));
        Route r2 = new Route(Arrays.asList(5, 2, 8));
        Assert.assertEquals(GossipCentral.DRIVERS_MISSING_GOSSIP_OUTPUT,
                GossipCentral.driveUntilAllGossipExchanged(Arrays.asList(r1, r2)));
    }

}