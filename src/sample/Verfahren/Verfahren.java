package sample.Verfahren;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Utility.Point;

import java.util.ArrayList;

public abstract class Verfahren {
    private Expression expression;
    private String function;
    private int startValue, endValue, accuracy, max;

    public Verfahren(String function, int start, int end, int accuracy, int max) {
        this.function = function;
        this.startValue = start;
        this.endValue = end;
        this.accuracy = accuracy;
        this.max = max;
        this.expression = new ExpressionBuilder(function).variable("x").build();
    }

    /**
     * Calculates Points until it reaches zero
     *
     * @return Point-Array
     */
    public abstract Point[] calculate();

    // Methods for Subclasses
    public String getFunction() {
        return function;
    }

    public int getStartValue() {
        return startValue;
    }

    public int getEndValue() {
        return endValue;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Expression getExpression() {
        return expression;
    }

    public int getMax() {
        return max;
    }

    protected Double roundDouble(double val) {
        return ((int)(val * Math.pow(10, accuracy))) / Math.pow(10, accuracy);
    }

    /**
     * Returns the X value whose Y value is closest to zero
     *
     * @return Double - X Value
     */
    protected Double findClosestToZero() {
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

    protected void addPoint(double x, ArrayList<Point> points) {
        double y = getExpression().setVariable("x", x).evaluate();
        points.add(new Point(roundDouble(x), roundDouble(y)));
    }
}
