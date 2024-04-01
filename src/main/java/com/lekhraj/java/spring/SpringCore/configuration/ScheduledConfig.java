package com.lekhraj.java.spring.SpringCore.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledConfig {
    //@Scheduled(cron = "0 15 10 15 * ?")
    public void task1() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println("schedule task-1, using cron jobs - " + now);
    }

    @Scheduled(fixedRate = 5000)
    public void task2() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
}
