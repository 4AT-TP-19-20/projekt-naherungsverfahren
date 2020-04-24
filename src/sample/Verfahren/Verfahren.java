package sample.Verfahren;

public abstract class Verfahren {
    private String function;
    private int startValue, endValue, accuracy, max;

    abstract double calculation();

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

    public int getMax() {
        return max;
    }
}
