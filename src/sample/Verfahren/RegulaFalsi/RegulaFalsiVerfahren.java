package sample.Verfahren.RegulaFalsi;

import net.objecthunter.exp4j.Expression;
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
        Expression f = getExpression();
        double an = getStartValue(), bn = getEndValue(), yan = f.setVariable("x", an).evaluate(), ybn = f.setVariable("x", bn).evaluate();
        double xn, yxn;
        for (int i = 0; i < getMax(); i++) {
            xn = regula(an, yan, bn, ybn);
            yxn = f.setVariable("x", xn).evaluate();
            if (yan < 0 && yxn > 0 || yan > 0 && yxn < 0) {
                ybn = yxn;
                bn = xn;
            } else if (ybn < 0 && yxn > 0 || ybn > 0 && yxn < 0) {
                yan = yxn;
                an = xn;
            }
            if (roundDouble(ybn) == 0.0) {
                break;
            }
            points.add(new Point(roundDouble(xn), roundDouble(yxn)));
        }
        return points.toArray(new Point[0]);
    }

    private double regula(double an, double yan, double bn, double ybn) {
        return (an * ybn - bn * yan) / (ybn - yan);
    }
}
