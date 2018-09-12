package ch.puzzle.springboot.workshop.example.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public long add(int a, int b) {
        return a + b;
    }
}
