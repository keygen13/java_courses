package pkg4pointsMethod;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistancePositiveCoordinates() {
        Point p1 = new Point(1,5);
        Point p2 = new Point(4,1);

        Assert.assertEquals(p1.distance(p2),5);
    }

    @Test
    public void testDistanceNegativeCoordinates() {
        Point p1 = new Point(-1,-1);
        Point p2 = new Point(-1,-3);

        Assert.assertEquals(p1.distance(p2),2);
    }

    @Test
    public void testDistanceZeroCoordinate() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,-112);

        Assert.assertEquals(p1.distance(p2),112);
    }
}
