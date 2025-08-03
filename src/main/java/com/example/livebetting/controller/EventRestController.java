package com.example.livebetting.controller;

import com.example.livebetting.controller.response.EventRequest;
import com.example.livebetting.controller.response.EventResponse;
import com.example.livebetting.entity.Event;
import com.example.livebetting.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EventRestController {
    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventResponse> findAll() {
        List<Event> events = eventService.getAllEvents();
        return EventResponse.fromList(events);
    }

    @GetMapping("/events/{eventId}")
    public EventResponse getEventById(@PathVariable int eventId) {
        Event event = eventService.getEventById(eventId);
        return EventResponse.from(event);
    }

    @PostMapping("/events")
    public List<EventRequest> addEvents(@RequestBody List<EventRequest> events) {
        List<Event> savedEvents = eventService.saveAllEvents(events);
        return EventRequest.fromList(savedEvents);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable int id, @RequestBody Map<String, String> body) {
        Event updatedEvent = eventService.updateEvent(id, body);
        return ResponseEntity.ok(EventResponse.from(updatedEvent));
    }
}