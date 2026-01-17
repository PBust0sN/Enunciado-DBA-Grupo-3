package com.example.ClimateChangeBackend;

import com.example.ClimateChangeBackend.services.MeasurePointsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private final MeasurePointsService measurePointsService;

    public ScheduledTasks(MeasurePointsService measurePointsService) {
        this.measurePointsService = measurePointsService;
    }
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
    @Scheduled(cron = "0 0 */6 * * *")
    public void scheduledRefresh() {
        //log.info("ACTUALIZANDO VISTA MATERIALIZADA");
        measurePointsService.refreshMonthlyTendency();
    }

}
