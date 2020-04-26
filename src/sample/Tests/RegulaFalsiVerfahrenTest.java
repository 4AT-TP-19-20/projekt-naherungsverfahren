import org.junit.jupiter.api.Test;
import sample.Utility.Point;
import sample.Verfahren.RegulaFalsi.RegulaFalsiVerfahren;
import sample.Verfahren.Verfahren;

import static org.junit.jupiter.api.Assertions.*;

class RegulaFalsiVerfahrenTest {

    @Test
    void calculate() {
        Verfahren v = new RegulaFalsiVerfahren("x^2 - 2", 0, 3, 5, 20);
        Point[] points = v.calculate();
        assertNotNull(points);
        assertNotEquals(0, points.length);
        Point p = points[points.length - 1];
        assertNotNull(p);
        assertEquals(p.y, 0);
        assertEquals(p.x, 1.41421);
    }
}