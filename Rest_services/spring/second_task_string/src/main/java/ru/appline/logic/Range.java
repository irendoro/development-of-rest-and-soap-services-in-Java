package ru.appline.logic;

public class Range {
    private int min;
    private int max;

    public Range(int start, int end) {
        this.min = start;
        this.max = end;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public boolean isInRange(int degree) {
        if (min <= max) {
            return degree >= min && degree <= max;
        } else {
            return degree >= min || degree <= max;
        }
    }
}
