package sample.Verfahren;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sample.Utility.Point;

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

    protected Double roundDouble(double x) {
        return ((int)(x * Math.pow(10, accuracy))) / Math.pow(10, accuracy);
    }
}
