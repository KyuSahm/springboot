package impl;

import intf.Calculator;

public class Operator {
    private Calculator calculator;

    public Operator(Calculator calculator) {
        this.calculator = calculator;
    }

    public int operateSum(int x, int y) {
        return calculator.sum(x, y);
    }

    public int operateMinus(int x, int y) {
        return calculator.minus(x, y);
    }
}