package com.example.livebetting.service;

import com.example.livebetting.entity.Event;
import com.example.livebetting.exceptionHandler.MissingArgumentException;
import com.example.livebetting.exceptionHandler.WrongArgumentException;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EventValidationService {


    public void validateAndApplyUpdates(Event event, Map<String, String> updates, int eventId) {
        try {
            boolean hasUpdates = false;

            if (updates.containsKey("homeWinOdds")) {
                double homeWinOdds = Double.parseDouble(updates.get("homeWinOdds"));
                validateOddRange(homeWinOdds, "Home win odds");
                event.setHomeWinOdds(homeWinOdds);
                hasUpdates = true;
            }

            if (updates.containsKey("drawOdds")) {
                double drawOdds = Double.parseDouble(updates.get("drawOdds"));
                validateOddRange(drawOdds, "Draw odds");
                event.setDrawOdds(drawOdds);
                hasUpdates = true;
            }

            if (updates.containsKey("awayWinOdds")) {
                double awayWinOdds = Double.parseDouble(updates.get("awayWinOdds"));
                validateOddRange(awayWinOdds, "Away win odds");
                event.setAwayWinOdds(awayWinOdds);
                hasUpdates = true;
            }

            // Business rule: At least one field must be provided for update
            if (!hasUpdates) {
                throw new MissingArgumentException("No valid fields to update for event id: " + eventId +
                        " - please provide at least one of: homeWinOdds, drawOdds, awayWinOdds");
            }

        } catch (NumberFormatException e) {
            throw new WrongArgumentException("Invalid number format in request body");
        }
    }

    // Validation methods (private - internal business rules)
    public void validateEventId(int eventId) {
        if (eventId <= 0) {
            throw new WrongArgumentException("Event id must be positive: " + eventId);
        }
    }

    public void validateEventData(Event event) {
        validateRequiredFields(event);
        validateOddValues(event);
    }

    public void validateRequiredFields(Event event) {
        if (event.getLeagueName() == null || event.getLeagueName().trim().isEmpty()) {


            throw new MissingArgumentException("League name must be provided");
        }

        if (event.getHomeTeamName() == null || event.getHomeTeamName().trim().isEmpty()) {
            throw new MissingArgumentException("Home team name must be provided");
        }

        if (event.getAwayTeamName() == null || event.getAwayTeamName().trim().isEmpty()) {
            throw new MissingArgumentException("Away team name must be provided");
        }

        if (event.getStartDateTime() == null || event.getStartDateTime().trim().isEmpty()) {
            throw new MissingArgumentException("Start date time must be provided");
        }

        // Check for zero values (assuming 0.0 means not set)
        if (event.getHomeWinOdds() == 0.0) {
            throw new MissingArgumentException("Home win odds must be provided");
        }

        if (event.getDrawOdds() == 0.0) {
            throw new MissingArgumentException("Draw odds must be provided");
        }

        if (event.getAwayWinOdds() == 0.0) {
            throw new MissingArgumentException("Away win odds must be provided");
        }
    }

    public void validateOddValues(Event event) {
        validateOddRange(event.getHomeWinOdds(), "Home win odds");
        validateOddRange(event.getDrawOdds(), "Draw odds");
        validateOddRange(event.getAwayWinOdds(), "Away win odds");
    }

    public void validateOddRange(double odds, String fieldName) {
        if (odds <= 1.0 || odds >= 10.0) {
            throw new WrongArgumentException(fieldName + " must be between 1 and 10");
        }
    }

}