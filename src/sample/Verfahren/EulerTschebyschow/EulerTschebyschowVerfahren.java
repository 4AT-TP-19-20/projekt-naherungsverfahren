package sample.Verfahren.EulerTschebyschow;

import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class EulerTschebyschowVerfahren extends Verfahren {

    public EulerTschebyschowVerfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();

        return points.toArray(new Point[0]);
    }
}
