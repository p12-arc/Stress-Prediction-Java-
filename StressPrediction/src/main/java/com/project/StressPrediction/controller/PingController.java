package com.project.StressPrediction.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@RestController
public class PingController {

    private final Random random = new Random();

    // To store last request time (rate limiting)
    private long lastCallTime = 0;

    @Async // makes this method run asynchronously
    @GetMapping("/ping")
    public synchronized CompletableFuture<String> ping() {

        long currentTime = System.currentTimeMillis();

        // Rate limit: allow only 1 request every 5 seconds
        if (currentTime - lastCallTime < 5000) {
            return CompletableFuture.completedFuture(
                "Too many requests. Please wait."
            );
        }

        lastCallTime = currentTime;

        // Random calculation
        int a = random.nextInt(1000);
        int b = random.nextInt(1000);

        long result = 1;
        for (int i = 1; i <= a; i++) {
            result = (result * b) % 1000000007; // avoid overflow
        }

        return CompletableFuture.completedFuture(
            "OK | calc=" + result
        );
    }
}
