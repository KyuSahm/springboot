package com.example.calculator.component;

import com.example.calculator.intf.Calculator;
import org.springframework.stereotype.Component;

@Component
public class DollorCalculator implements Calculator {
    private int ratio;
    private final MarketAPI marketAPI;

    public DollorCalculator(MarketAPI marketAPI) {
        this.marketAPI = marketAPI;
        init();
    }

    private void init() {
        this.ratio = marketAPI.getRealTimeRatio();
    }

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
