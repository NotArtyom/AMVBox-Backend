package ru.itis.demo.schedulers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.itis.demo.service.StatisticsService;

@Configuration
@EnableScheduling
@Slf4j
public class StatisticsScheduler {

    @Autowired
    private StatisticsService statisticsService;

    @Scheduled(cron = "0 0 0 * * 0")
    public void run() {
        statisticsService.sendStats();
    }
}
