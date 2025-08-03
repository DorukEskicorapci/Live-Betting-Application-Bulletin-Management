package com.example.livebetting.schedulingtasks;

import com.example.livebetting.service.OddsUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final OddsUpdateService oddsUpdateService;

    @Autowired
    public ScheduledTasks(OddsUpdateService oddsUpdateService) {
        this.oddsUpdateService = oddsUpdateService;
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleOddsUpdate() {
        logger.debug("Starting scheduled odds update");
        oddsUpdateService.updateAllEventOdds();
        logger.debug("Completed scheduled odds update");
    }
}