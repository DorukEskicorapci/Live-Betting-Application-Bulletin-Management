package com.example.livebetting.service;

import com.example.livebetting.controller.response.EventRequest;
import com.example.livebetting.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventConversionService {

    public List<Event> convertToEntities(List<EventRequest> eventRequests) {
        return eventRequests.stream()
                .map(EventRequest::toEntity)
                .collect(Collectors.toList());
    }

    public void prepareForInsert(List<Event> events) {
        events.forEach(event -> event.setEventId(0));
    }
}