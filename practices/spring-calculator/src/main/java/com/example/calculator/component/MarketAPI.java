package com.example.calculator.component;

import org.springframework.stereotype.Component;

@Component
public class MarketAPI {
    protected int getRealTimeRatio() {
        // get ratio from naver, kakao
        return 1200;
    }
}
