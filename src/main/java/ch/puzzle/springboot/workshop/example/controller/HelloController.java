package ch.puzzle.springboot.workshop.example.controller;

import ch.puzzle.springboot.workshop.example.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    CalculatorService calculatorService;

    @Value("${app.message:No message defined }")
    private String message;

    @GetMapping
    public String sayHello() {
        return message;
    }

    @PostMapping
    public String sayHelloWithName(@RequestBody String input) {
        return message + input;
    }

    @GetMapping("add")
    public Long addNumbers(@RequestParam Integer a, @RequestParam Integer b) {
        return calculatorService.add(a,b);
    }

}
