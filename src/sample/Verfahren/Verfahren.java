package sample.Verfahren;

import sample.Utility.Point;

public abstract class Verfahren {
    private String function;
    private int startValue, endValue, accuracy, max;

    public Verfahren(String function, int start, int end, int accuracy, int max) {
        this.function = function;
        this.startValue = start;
        this.endValue = end;
        this.accuracy = accuracy;
        this.max = max;
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

    // Pattern for DecimalFormat
    public String getPattern() {
        StringBuilder sb = new StringBuilder("#.");
        for(int i = 0; i < getAccuracy(); i++) {
            sb.append("#");
        }

        return sb.toString();
    }

    public int getMax() {
        return max;
    }
}
