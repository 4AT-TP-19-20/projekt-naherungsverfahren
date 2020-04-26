package sample.Verfahren.Bisektion;

import net.objecthunter.exp4j.Expression;
import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.util.ArrayList;

public class Bisektionsverfahren extends Verfahren {

    public Bisektionsverfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();
        Expression f = getExpression();
        double an = getStartValue(), xn, bn = getEndValue(), yan = f.setVariable("x", an).evaluate(), ybn = f.setVariable("x", bn).evaluate(), yxn;
        for (int i = 0; i < getMax(); i++) {
            xn = intervallHalbierung(an, bn);
            yxn = f.setVariable("x", xn).evaluate();
            if(yan < 0 && yxn > 0 || yan > 0 && yan < 0){
                bn = xn;
                ybn = yxn;
            }
            if(ybn > 0 && yxn < 0 || ybn < 0 && yxn > 0){
                an = xn;
                yan = yxn;
            }
            points.add(new Point(roundDouble(xn), roundDouble(yxn)));
        }
        return points.toArray(new Point[0]);
    }

    private double intervallHalbierung(double x, double y){
        return (x + y) / 2;
    }
}
