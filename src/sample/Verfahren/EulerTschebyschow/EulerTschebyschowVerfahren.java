package sample.Verfahren.EulerTschebyschow;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Utility.Funktion;
import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.io.IOException;
import java.util.ArrayList;

public class EulerTschebyschowVerfahren extends Verfahren {

    public EulerTschebyschowVerfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();

        try {
            String s_f1 = Funktion.derive(getFunction());
            String s_f2 = Funktion.derive(s_f1);

            if(s_f1 != null && s_f2 != null) {
                Expression f1 = new ExpressionBuilder(s_f1).variable("x").build();
                Expression f2 = new ExpressionBuilder(s_f2).variable("x").build();

                double x = findClosestToZero();
                addPoint(x, points);

                for(int i = 0; i < getMax(); i++) {
                    double x_f = f(x);
                    double x_f1 = f1.setVariable("x", x).evaluate();
                    double x_f2 = f2.setVariable("x", x).evaluate();

                    double s = - (x_f / x_f1);
                    double t = -0.5 * ((x_f2 * Math.pow(s, 2)) / x_f1);

                    x = x + s + t;
                    addPoint(x, points);

                    if(roundDouble(f(x)) == 0.0) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points.toArray(new Point[0]);
    }
}
