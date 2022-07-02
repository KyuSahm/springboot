package impl;

import intf.Calculator;

public class KRWCalculator implements Calculator {
    private int ratio = 1;
    @Override
    public int sum(int x, int y) {
        x *= ratio;
        y *= ratio;

        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x *= ratio;
        y *= ratio;

        return x - y;
    }
}
