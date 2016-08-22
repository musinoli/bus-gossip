import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class BusDriverTest {

    @Test
    public void canGossip() {
        Route route = Mockito.mock(Route.class);

        BusDriver driver1 = new BusDriver(8, route);
        BusDriver driver2 = new BusDriver(11, route);

        Assert.assertEquals(1, driver1.getGossip().size());
        Assert.assertTrue(driver1.getGossip().contains(8));

        driver1.addGossip(driver2.getGossip());

        Assert.assertEquals(2, driver1.getGossip().size());
        Assert.assertTrue(driver1.getGossip().containsAll(Arrays.asList(8, 11)));
    }

    @Test

    public void canGetCurrentStop() {
        Route route = Mockito.mock(Route.class);
        Mockito.when(route.nextStop()).thenReturn(6);

        BusDriver driver = new BusDriver(1, route);
        driver.drive();
        Assert.assertEquals(6, driver.getCurrentStop());
    }

}