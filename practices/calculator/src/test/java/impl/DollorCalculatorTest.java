package impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DollorCalculatorTest {
    @Mock
    public MarketAPI marketAPI;

    @BeforeEach
    public void init() {
        Mockito.lenient().when(marketAPI.getRealTimeRatio()).thenReturn(3000);
    }

    @Test
    public void testHello() {
        System.out.println("hello");
    }

    @Test
    public void dollorTest() {
        MarketAPI marketAPI = new MarketAPI();
        Operator operator = new Operator(new DollorCalculator(marketAPI));

        Assertions.assertEquals(24000, operator.operateSum(10, 10));
        Assertions.assertEquals(0, operator.operateMinus(10, 10));
    }

    @Test
    public void mockTest() {
        MarketAPI marketAPI = new MarketAPI();
        Operator operator = new Operator(new DollorCalculator(marketAPI));

        Assertions.assertEquals(marketAPI.getRealTimeRatio() * 20,
                operator.operateSum(10, 10));
        Assertions.assertEquals(0, operator.operateMinus(10, 10));
    }
}