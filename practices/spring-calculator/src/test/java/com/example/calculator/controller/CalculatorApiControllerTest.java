package com.example.calculator.controller;

import com.example.calculator.component.DollorCalculator;
import com.example.calculator.component.MarketAPI;
import com.example.calculator.component.Operator;
import com.example.calculator.dto.Request;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculatorApiController.class)
@Import({Operator.class, DollorCalculator.class})
class CalculatorApiControllerTest {
    // 아래의 형태로 작성시 context에 존재하지 않으므로, 에러 발생
    // @Autowired
    // Operator operator;

    @MockBean
    private MarketAPI marketAPI;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        Mockito.when(marketAPI.getRealTimeRatio()).thenReturn(3000);
    }

    @Test
    public void sumTestOnGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("60000"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void sumTestOnPost() throws Exception {
        Request request = new Request(20, 20);
        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.result").value(120000))
                .andDo(MockMvcResultHandlers.print());
    }
}