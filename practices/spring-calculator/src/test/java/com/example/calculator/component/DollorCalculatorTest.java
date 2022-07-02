package com.example.calculator.component;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@Import(MarketAPI.class)
class DollorCalculatorTest {
    @MockBean
    private MarketAPI marketAPI;

}