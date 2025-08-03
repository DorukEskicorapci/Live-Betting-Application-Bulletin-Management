package com.example.livebetting.controller.response;

import com.example.livebetting.entity.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class EventRequest {
    private int eventId;
    private String leagueName;
    private String homeTeamName;
    private String awayTeamName;
    private double homeWinOdds;
    private double drawOdds;
    private double awayWinOdds;
    private String startDateTime;

    public EventRequest(Event event) {
        this.eventId = event.getEventId();
        this.leagueName = event.getLeagueName();
        this.homeTeamName = event.getHomeTeamName();
        this.awayTeamName = event.getAwayTeamName();
        this.homeWinOdds = event.getHomeWinOdds();
        this.drawOdds = event.getDrawOdds();
        this.awayWinOdds = event.getAwayWinOdds();
        this.startDateTime = event.getStartDateTime();
    }

    public static List<EventRequest> fromList(List<Event> events) {
        return events.stream()
                .map(EventRequest::new)
                .collect(Collectors.toList());
    }

    public Event toEntity() {
        Event event = new Event();
        event.setEventId(this.eventId);
        event.setLeagueName(this.leagueName);
        event.setHomeTeamName(this.homeTeamName);
        event.setAwayTeamName(this.awayTeamName);
        event.setHomeWinOdds(this.homeWinOdds);
        event.setDrawOdds(this.drawOdds);
        event.setAwayWinOdds(this.awayWinOdds);
        event.setStartDateTime(this.startDateTime);
        return event;
    }
}