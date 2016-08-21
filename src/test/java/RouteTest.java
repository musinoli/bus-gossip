import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RouteTest {

    @Test
    public void providesNextStop() {
        List<Integer> stops = Arrays.asList(5, 2, 7, 3);
        Route route = new Route(stops);

        Assert.assertEquals(5, route.nextStop());
        Assert.assertEquals(2, route.nextStop());
        Assert.assertEquals(7, route.nextStop());
        Assert.assertEquals(3, route.nextStop());
        Assert.assertEquals(5, route.nextStop());
    }
}