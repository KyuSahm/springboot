package com.example.calculator.controller;

import com.example.calculator.component.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalculatorApiController {
    private final Operator operator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y) {
        return operator.operateSum(x, y);
    }

    @GetMapping("/minus")
    public int minus(@RequestParam int x, @RequestParam int y) {
        return operator.operateMinus(x, y);
    }

}
