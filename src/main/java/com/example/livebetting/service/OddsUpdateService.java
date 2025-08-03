package com.example.livebetting.service;

import com.example.livebetting.controller.response.EventResponse;
import com.example.livebetting.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


@Service
public class OddsUpdateService {
    private static final Logger logger = LoggerFactory.getLogger(OddsUpdateService.class);
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("0.00");

    private final EventService eventService;
    private final OddsGeneratorService oddsGeneratorService;

    @Autowired
    public OddsUpdateService(EventService eventService, OddsGeneratorService oddsGeneratorService) {
        this.eventService = eventService;
        this.oddsGeneratorService = oddsGeneratorService;
    }

    public void updateAllEventOdds() {
        List<Event> allEventsEntities = eventService.getAllEvents();
        List<EventResponse> allEvents = EventResponse.fromList(allEventsEntities);

        for (EventResponse event : allEvents) {
            updateSingleEventOdds(event);
        }

        logger.info("Updated odds for {} events", allEvents.size());
    }

    private void updateSingleEventOdds(EventResponse event) {
        double[] odds = oddsGeneratorService.generateValidOdds();
        int eventId = event.getEventId();

        Map<String, String> oddsUpdatePayload = formatOddsPayload(odds);

        eventService.updateEvent(eventId, oddsUpdatePayload);

        logOddsUpdate(event.getEventId(), odds);
    }


    private Map<String, String> formatOddsPayload(double[] odds) {
        return Map.of(
            "homeWinOdds", NUMBER_FORMAT.format(odds[0]),
            "drawOdds", NUMBER_FORMAT.format(odds[1]),
            "awayWinOdds", NUMBER_FORMAT.format(odds[2])
        );
    }

    private void logOddsUpdate(int eventId, double[] odds) {
        logger.info("Updated event ID: {} - Home: {}, Draw: {}, Away: {}",
                eventId,
                NUMBER_FORMAT.format(odds[0]),
                NUMBER_FORMAT.format(odds[1]),
                NUMBER_FORMAT.format(odds[2]));
    }
}
