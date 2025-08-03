package com.example.livebetting.service;

import com.example.livebetting.controller.response.EventRequest;

import com.example.livebetting.dao.EventRepository;
import com.example.livebetting.entity.Event;
import com.example.livebetting.exceptionHandler.MissingArgumentException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventService {
    private final EventRepository eventRepo;
    private final EventValidationService validationService;
    private final EventConversionService conversionService;
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    public EventService(EventRepository eventRepo,
                        EventValidationService validationService,
                        EventConversionService conversionService) {
        this.eventRepo = eventRepo;
        this.validationService = validationService;
        this.conversionService = conversionService;
    }

    public List<Event> getAllEvents() {
        logger.debug("Finding all events");
        List<Event> events = eventRepo.findAll();
        logger.info("Found {} events", events.size());
        return events;
    }

    public Event getEventById(int eventId) {
        validationService.validateEventId(eventId);
        Event event = eventRepo.findById(eventId).orElse(null);
        if (event == null) {
            throw new MissingArgumentException("Event not found with id: " + eventId);
        }
        return event;
    }

    public List<Event> saveAllEvents(List<EventRequest> eventRequests) {
        List<Event> eventEntities = conversionService.convertToEntities(eventRequests);
        eventEntities.forEach(validationService::validateEventData);
        conversionService.prepareForInsert(eventEntities);

        logger.info("Successfully saved {} event(s)", eventEntities.size());
        return eventRepo.saveAll(eventEntities);
    }

    public Event updateEvent(int eventId, Map<String, String> updates) {
        validationService.validateEventId(eventId);

        Event event = eventRepo.findById(eventId).orElse(null);
        if (event == null) {
            throw new MissingArgumentException("Event not found with id: " + eventId);
        }

        validationService.validateAndApplyUpdates(event, updates, eventId);
        return eventRepo.save(event);
    }
}