package sample.Verfahren.Newton;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Utility.Funktion;
import sample.Utility.Point;
import sample.Verfahren.Verfahren;

import java.io.IOException;
import java.util.ArrayList;

public class NewtonVerfahren extends Verfahren {

    public NewtonVerfahren(String function, int start, int end, int accuracy, int max) {
        super(function, start, end, accuracy, max);
    }

    @Override
    public Point[] calculate() {
        ArrayList<Point> points = new ArrayList<>();
        try {
            String derive_function = Funktion.derive(getFunction());

            if(derive_function != null) {
                Expression f = getExpression();
                double xn = findClosestToZero();
                points.add(new Point(roundDouble(xn), roundDouble(f.setVariable("x", xn).evaluate())));

                for(int i = 0; i < getMax(); i++) {
                    double f_value = f.setVariable("x", xn).evaluate();
                    double fd_value = new ExpressionBuilder(derive_function).variable("x").build().setVariable("x", xn).evaluate();

                    xn = xn - (f_value / fd_value);
                    points.add(new Point(roundDouble(xn), roundDouble(f.setVariable("x", xn).evaluate())));

                    if(roundDouble(f.setVariable("x", xn).evaluate()) == 0.0) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points.toArray(new Point[0]);
    }

    /**
     * Returns the X value whose Y value is closest to zero
     *
     * @return Double - X Value
     */
    private Double findClosestToZero() {
        double step = (Math.max(getStartValue(), getEndValue()) - Math.min(getStartValue(), getEndValue())) / 6.0;
        Double d = null;

        for(double x = getStartValue(); x <= getEndValue(); x+=step) {
            if(d != null) {
                double val = new ExpressionBuilder(getFunction()).variable("x").build().setVariable("x", x).evaluate();
                double current = new ExpressionBuilder(getFunction()).variable("x").build().setVariable("x", d).evaluate();
                if(Math.abs(current) > Math.abs(val)) {
                    d = x;
                }
            } else {
                d = x;
            }
        }

        return d;
    }
}
