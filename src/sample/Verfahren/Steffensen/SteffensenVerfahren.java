package sample.Verfahren.Steffensen;

import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class SteffensenVerfahren extends Verfahren {

    public SteffensenVerfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    public SteffensenVerfahren() {
        super();
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();

        double xn = findClosestToZero();
        addPoint(xn, points);

        for(int i = 0; i < getMax(); i++) {
            xn = xn - f(xn) / g(xn);
            addPoint(xn, points);

            if(roundDouble(f(xn)) == 0.0) {
                break;
            }
        }

        return points.toArray(new Point[0]);
    }

    @Override
    public String getInformation() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }

    private double g(double x) {
        return (f( x + f(x)) / f(x)) - 1;
    }
}
