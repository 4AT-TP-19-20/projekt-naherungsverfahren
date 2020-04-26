import org.junit.jupiter.api.Test;
import sample.Utility.Point;
import sample.Verfahren.Steffensen.SteffensenVerfahren;
import sample.Verfahren.Verfahren;

import static org.junit.jupiter.api.Assertions.*;

class SteffensenVerfahrenTest {

    @Test
    void calculate() {
        Verfahren v = new SteffensenVerfahren("x^2 - 2", 1, 3, 5, 10);
        Point[] points = v.calculate();
        assertNotNull(points);
        assertNotEquals(0, points.length);
        Point p = points[points.length - 1];
        assertNotNull(p);
        assertEquals(p.y, 0);
        assertEquals(p.x, 1.41421);
    }
}