package com.example.calculator.component;

import com.example.calculator.intf.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest
// @Import({MarketAPI.class, DollorCalculator.class})
class OperatorTest {
    @MockBean
    private MarketAPI marketAPI;

    @Autowired
    private Calculator calculator;

    @BeforeEach
    public void init() {
        Mockito.lenient().when(marketAPI.getRealTimeRatio()).thenReturn(2000);
        calculator.init();
    }

    @Test
    public void dollarCalculatorTest() {
        int sum = calculator.sum(10, 10);
        Assertions.assertEquals(40000, sum);

        int minus = calculator.minus(10, 10);
        Assertions.assertEquals(0, minus);
    }
}