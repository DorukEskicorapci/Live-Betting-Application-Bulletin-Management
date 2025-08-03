package com.example.livebetting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "league_name")
    private String leagueName;

    @Column(name = "home_team_name")
    private String homeTeamName;

    @Column(name = "away_team_name")
    private String awayTeamName;

    @Column(name = "home_win_odds")
    private double homeWinOdds;

    @Column(name = "draw_odds")
    private double drawOdds;

    @Column(name = "away_win_odds")
    private double awayWinOdds;

    @Column(name = "start_date_time")
    private String startDateTime;

    // -----------------------
    // Constructors
    // -----------------------

    public Event() {
        // Default no-args constructor
    }

    public Event(
            String leagueName,
            String homeTeamName,
            String awayTeamName,
            double homeWinOdds,
            double drawOdds,
            double awayWinOdds,
            String startDateTime
    ) {
        this.leagueName = leagueName;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeWinOdds = homeWinOdds;
        this.drawOdds = drawOdds;
        this.awayWinOdds = awayWinOdds;
        this.startDateTime = startDateTime;
    }


    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", leagueName='" + leagueName + '\'' +
                ", homeTeamName='" + homeTeamName + '\'' +
                ", awayTeamName='" + awayTeamName + '\'' +
                ", homeWinOdds=" + homeWinOdds +
                ", drawOdds=" + drawOdds +
                ", awayWinOdds=" + awayWinOdds +
                ", startDateTime='" + startDateTime + '\'' +
                '}';
    }


}
