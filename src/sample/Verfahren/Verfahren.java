package sample.Verfahren;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Utility.Point;

import java.util.ArrayList;

public abstract class Verfahren {

    private Expression expression;
    private String function;
    private double startValue;
    private double endValue;
    private int accuracy;
    private int maxValue;

    public Verfahren(String function, double start, double end, int accuracy, int maxValue) {
        this.function = function;
        this.startValue = start;
        this.endValue = end;
        this.accuracy = accuracy;
        this.maxValue = maxValue;
        this.expression = function == null ? null : new ExpressionBuilder(function).variable("x").build();
    }

    public Verfahren() {
        this(null , 0, 0, 0, 0);
    }
    /**
     * Calculates Points until it reaches zero
     *
     * @return Point-Array
     */
    public abstract Point[] calculate();

    public abstract String toString();



    protected double getStartValue() {
        return startValue;
    }

    protected double getEndValue() {
        return endValue;
    }

    protected int getMax() {
        return maxValue;
    }

    protected String getFunction() {
        return function;
    }

    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Returns the corresponding Y Value
     *
     * @param x The X Value whose Y Value should be returned
     * @return double
     */
    public double f(double x) {
        return expression.setVariable("x", x).evaluate();
    }

    public void setFunction(String function) {
        this.function = function;
        this.expression = function == null ? null : new ExpressionBuilder(function).variable("x").build();
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setMax(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Returns the val Value with accuracy times decimal places
     *
     * @param val The double which should be rounded
     * @return Double
     */
    protected Double roundDouble(double val) {
        return ((int)(val * Math.pow(10, accuracy))) / Math.pow(10, accuracy);
    }

    /**
     * Returns the X value whose Y value is closest to zero
     *
     * @return Double - X Value
     */
    protected Double findClosestToZero() {
        double step = (Math.max(startValue, endValue) - Math.min(startValue, endValue)) / 6.0;
        Double d = null;

        for(double x = startValue; x <= endValue; x+=step) {
            if(d != null) {
                double val = new ExpressionBuilder(function).variable("x").build().setVariable("x", x).evaluate();
                double current = new ExpressionBuilder(function).variable("x").build().setVariable("x", d).evaluate();
                if(Math.abs(current) > Math.abs(val)) {
                    d = x;
                }
            } else {
                d = x;
            }
        }

        return d;
    }

    /**
     * Adds a new Point to the Array. It creates an Object Point with the Values X and Y.
     * Values have "accuracy" times decimal places.
     *
     * @param x - The X Value to Add
     * @param points - The Array
     */
    protected void addPoint(double x, ArrayList<Point> points) {
        double y = expression.setVariable("x", x).evaluate();
        points.add(new Point(roundDouble(x), roundDouble(y)));
    }
}
