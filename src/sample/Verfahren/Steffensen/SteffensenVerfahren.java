package sample.Verfahren.Steffensen;

import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class SteffensenVerfahren extends Verfahren {

    public SteffensenVerfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();

        double xn = findClosestToZero();
        addPoint(xn, points);

        for(int i = 0; i < getMax(); i++) {
            xn = xn - f(xn) / g(xn);
            addPoint(xn, points);
        }

        return points.toArray(new Point[0]);
    }

    private double g(double x) {
        return (f( x + f(x)) / f(x)) - 1;
    }
}
