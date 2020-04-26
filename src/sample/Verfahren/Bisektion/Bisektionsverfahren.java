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

    @Override
    public String getInformation() {
        return null;
    }

    private double intervallHalbierung(double x, double y){
        return (x + y) / 2;
    }

    @Override
    public String toString() {
        return "Beim Newton-Verfahren (auch Tangenten- bzw. Tangentennäherungsverfahren genannt) wird die Sekante von Regula Falsi durch die Tangente am Punkt x0 ersetzt. Die Nullstelle dieser Tangente wird dann als verbesserte Näherung der Nullstelle der Funktion verwendet. Dann wird wieder eine Tangente durch den Punkt gelegt und immer so weiter. Somit nähert man sich immer weiter der gesuchten Nullstelle.";
    }
}
