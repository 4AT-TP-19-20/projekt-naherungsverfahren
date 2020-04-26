package sample.Verfahren.RegulaFalsi;

import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class RegulaFalsiVerfahren extends Verfahren {

    public RegulaFalsiVerfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();

        double an = getStartValue();
        double bn = getEndValue();
        double yan = f(an);
        double ybn = f(bn);

        for (int i = 0; i < getMax(); i++) {
            double xn = regula(an, yan, bn, ybn);
            double yxn = f(xn);

            if ((yan < 0 && yxn > 0) || (yan > 0 && yxn < 0)) {
                ybn = yxn;
                bn = xn;
            } else if ((ybn < 0 && yxn > 0) || (ybn > 0 && yxn < 0)) {
                yan = yxn;
                an = xn;
            }

            addPoint(xn, points);

            if (roundDouble(ybn) == 0.0) {
                break;
            }
        }

        return points.toArray(new Point[0]);
    }

    private double regula(double an, double yan, double bn, double ybn) {
        return (an * ybn - bn * yan) / (ybn - yan);
    }
}
