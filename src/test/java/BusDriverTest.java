import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BusDriverTest {

    @Test
    public void canGossip() {
        Route route = Mockito.mock(Route.class);

        BusDriver driver1 = new BusDriver(route);
        BusDriver driver2 = new BusDriver(route);

        Assert.assertEquals(1, driver1.getGossip());
        Assert.assertEquals(1, driver2.getGossip());
    }

    @Test

    public void canGetCurrentStop() {
        Route route = Mockito.mock(Route.class);
        Mockito.when(route.nextStop()).thenReturn(6);

        BusDriver driver = new BusDriver(route);
        driver.drive();
        Assert.assertEquals(6, driver.getCurrentStop());
    }

}