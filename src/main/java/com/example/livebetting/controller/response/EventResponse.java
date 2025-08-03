package com.example.livebetting.controller.response;

import com.example.livebetting.entity.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class EventResponse {
    private int eventId;
    private String leagueName;
    private String homeTeamName;
    private String awayTeamName;
    private double homeWinOdds;
    private double drawOdds;
    private double awayWinOdds;


    public EventResponse(Event event) {
        this.eventId = event.getEventId();
        this.leagueName = event.getLeagueName();
        this.homeTeamName = event.getHomeTeamName();
        this.awayTeamName = event.getAwayTeamName();
        this.homeWinOdds = event.getHomeWinOdds();
        this.drawOdds = event.getDrawOdds();
        this.awayWinOdds = event.getAwayWinOdds();
        //this.startDateTime = event.getStartDateTime();
    }

    public static EventResponse from(Event event) {
        if (event == null) {
            return null;
        }
        return new EventResponse(event);
    }

    public static List<EventResponse> fromList(List<Event> events) {
        return events.stream()
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }

}