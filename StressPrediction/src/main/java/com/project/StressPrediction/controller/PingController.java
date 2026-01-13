package com.project.StressPrediction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PingController {

    private final Random random = new Random();

    @GetMapping("/ping")
    public String ping() {

        // Random calculation
        int a = random.nextInt(1000);
        int b = random.nextInt(1000);

        long result = 1;
        for (int i = 1; i <= a; i++) {
            result = (result * b) % 1000000007; // avoid overflow
        }

        return "OK | calc=" + result;
    }
}
