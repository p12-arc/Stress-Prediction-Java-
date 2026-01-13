package com.project.StressPrediction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class StressPredictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(StressPredictionApplication.class, args);
    }

    // Pings every 2 minutes
    @Scheduled(fixedRate = 120000) // 2 mins
    public void keepAlive() {
        try {
            new RestTemplate()
                .getForObject(
                    "https://stressometer-msrw.onrender.com/ping",
                    String.class
                );
            System.out.println("Ping success");
        } catch (Exception e) {
            System.out.println("Ping failed: " + e.getMessage());
        }
    }
}
