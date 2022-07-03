package com.example.calculator.controller;

import com.example.calculator.component.Operator;
import com.example.calculator.dto.Request;
import com.example.calculator.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalculatorApiController {
    private final Operator operator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y)
    {
        operator.operateInit();
        return operator.operateSum(x, y);
    }

    @GetMapping("/minus")
    public int minus(@RequestParam int x, @RequestParam int y)
    {
        operator.operateInit();
        return operator.operateMinus(x, y);
    }

    @PostMapping("/sum")
    public Response sumPost(@RequestBody Request req)
    {
        operator.operateInit();
        int result = operator.operateSum(req.getX(), req.getY());
        return new Response("ok", new Response.Body(result));
    }

    @PostMapping("/minus")
    public Response minusPost(@RequestBody Request req)
    {
        operator.operateInit();
        int result = operator.operateMinus(req.getX(), req.getY());
        return new Response("ok", new Response.Body(result));
    }
}
