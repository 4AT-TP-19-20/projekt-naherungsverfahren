import org.junit.jupiter.api.Test;
import sample.Utility.Point;
import sample.Verfahren.EulerTschebyschow.EulerTschebyschowVerfahren;
import sample.Verfahren.Newton.NewtonVerfahren;
import sample.Verfahren.Verfahren;

import static org.junit.jupiter.api.Assertions.*;

class EulerTschebyschowVerfahrenTest {

    @Test
    void calculate() {
        Verfahren v = new EulerTschebyschowVerfahren("x^2 - 2", 0, 3, 5, 10);
        Point[] points = v.calculate();
        assertNotNull(points);
        assertNotEquals(0, points.length);
        Point p = points[points.length - 1];
        assertNotNull(p);
        assertEquals(p.y, 0);
        assertEquals(p.x, 1.41421);
    }
}