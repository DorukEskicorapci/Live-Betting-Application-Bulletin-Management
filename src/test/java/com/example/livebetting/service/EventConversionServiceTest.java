package com.example.livebetting.service;

import com.example.livebetting.controller.response.EventRequest;
import com.example.livebetting.entity.Event;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventConversionServiceTest {


    @Test
    public void testConvertToEntities_WithValidData() {
        EventRequest eventRequest1 = mock(EventRequest.class);
        EventRequest eventRequest2 = mock(EventRequest.class);

        Event event1 = new Event("League A", "Home Team A", "Away Team A", 1.5, 3.5, 2.5, "2025-08-03T21:00:00");
        Event event2 = new Event("League B", "Home Team B", "Away Team B", 1.8, 3.2, 2.1, "2025-08-04T18:00:00");

        when(eventRequest1.toEntity()).thenReturn(event1);
        when(eventRequest2.toEntity()).thenReturn(event2);

        List<EventRequest> eventRequests = Arrays.asList(eventRequest1, eventRequest2);

        EventConversionService eventConversionService = new EventConversionService();

        List<Event> result = eventConversionService.convertToEntities(eventRequests);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(event1.getLeagueName(), result.get(0).getLeagueName());
        assertEquals(event2.getLeagueName(), result.get(1).getLeagueName());
    }

    @Test
    public void testConvertToEntities_WithEmptyList() {
        List<EventRequest> eventRequests = List.of();
        EventConversionService eventConversionService = new EventConversionService();

        List<Event> result = eventConversionService.convertToEntities(eventRequests);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testConvertToEntities_WithNullList() {
        EventConversionService eventConversionService = new EventConversionService();

        try {
            eventConversionService.convertToEntities(null);
        } catch (NullPointerException ex) {
            assertNotNull(ex);
        }
    }
}