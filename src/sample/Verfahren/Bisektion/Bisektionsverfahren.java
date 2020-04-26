package sample.Verfahren.Bisektion;

import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class Bisektionsverfahren extends Verfahren {

    public Bisektionsverfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    public Bisektionsverfahren() {
        super();
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();

        double an = getStartValue();
        double bn = getEndValue();
        double yan = f(an);
        double ybn = f(bn);

        for (int i = 0; i < getMax(); i++) {
            double xn = intervallHalbierung(an, bn);
            double yxn = f(xn);

            if(yan < 0 && yxn > 0 || yan > 0 && yan < 0){
                bn = xn;
                ybn = yxn;
            }
            if(ybn > 0 && yxn < 0 || ybn < 0 && yxn > 0){
                an = xn;
                yan = yxn;
            }
            addPoint(xn, points);

            if(roundDouble(yxn) == 0.0) {
                break;
            }
        }
        return points.toArray(new Point[0]);
    }

    private double intervallHalbierung(double x, double y){
        return (x + y) / 2;
    }

    @Override
    public String toString() {
        return "Bisektions Verfahren";
    }
}
