package sample.Verfahren.Sekanten;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Utility.Funktion;
import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.io.IOException;
import java.util.ArrayList;

public class Sekantenverfahren extends Verfahren {
    static double[] xn = new double[100];
    static double[] f_xn = new double[100];

    public Sekantenverfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();
        Expression f = getExpression();
        double xn = Math.max(getStartValue(),getEndValue()), xn_ = Math.min(getStartValue(),getEndValue());
        double yn = f.setVariable("x", xn).evaluate();
        double yn_ = f.setVariable("x", xn_).evaluate();
        points.add(new Point(roundDouble(xn), roundDouble(yn)));
        for (int i = 0; i < getMax(); i++) {
            double temp = (xn_ * yn - xn * yn_)/(yn - yn_);
            xn_ = xn;
            yn_ = yn;
            xn = temp;
            yn = f.setVariable("x", xn).evaluate();
            points.add(new Point(roundDouble(xn), roundDouble(yn)));
            if(roundDouble(yn) == 0.0) {
                break;
            }
        }
        return points.toArray(new Point[0]);
    }

}
