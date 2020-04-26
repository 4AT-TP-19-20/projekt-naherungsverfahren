package sample.Verfahren.Sekanten;

import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class Sekantenverfahren extends Verfahren {

    public Sekantenverfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    public Sekantenverfahren() {
        super();
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();

        double xn = Math.max(getStartValue(), getEndValue());
        double xn_ = Math.min(getStartValue(), getEndValue());
        double yn = f(xn);
        double yn_ = f(xn_);

        addPoint(xn, points);

        for (int i = 0; i < getMax(); i++) {
            double temp = (xn_ * yn - xn * yn_)/(yn - yn_);

            xn_ = xn;
            yn_ = yn;
            xn = temp;
            yn = f(xn);

            addPoint(xn, points);

            if(roundDouble(yn) == 0.0) {
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
        return "Beim Sekantenverfahren handelt es sich im Prinzip um eine Vereinfachung des Newton-Verfahrens, weil man hier nicht die Ableitung der Funktion benötigt.\n" +
                "Beim Verfahren wird durch zwei Punkte der Funktion eine Sekante gelegt. Der Schnittpunkt mit der x-Achse (, der sich nun näher an der Nullstelle befindet), wird nun als x-Koordinate des neuen Punktes auf der Funktion verwendet und es wird eine Sekante durch die beiden Punkte gelegt. Somit nähert man sich immer weiter der Nullstelle.\n";
    }

}