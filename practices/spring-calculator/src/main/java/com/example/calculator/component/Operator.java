package com.example.calculator.component;

import com.example.calculator.intf.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Operator {
    private final Calculator calculator;

    public int operateSum(int x, int y) {
        return calculator.sum(x, y);
    }

    public int operateMinus(int x, int y) {
        return calculator.minus(x, y);
    }
}