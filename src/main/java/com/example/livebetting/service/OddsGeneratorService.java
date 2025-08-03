package com.example.livebetting.service;

import org.springframework.stereotype.Service;

@Service
public class OddsGeneratorService {

    public double[] generateValidOdds() {
        final double MIN_ODDS = 1.01;
        final double MAX_ODDS = 9.99;
        final double TARGET_SUM = 9.0;

        double homeOdds = MIN_ODDS + Math.random() * (MAX_ODDS - MIN_ODDS);
        double drawOdds = MIN_ODDS + Math.random() * (MAX_ODDS - MIN_ODDS);
        double awayOdds = TARGET_SUM - homeOdds - drawOdds;

        if (awayOdds < MIN_ODDS || awayOdds > MAX_ODDS) {
            return generateValidOdds();
        }

        return new double[]{homeOdds, drawOdds, awayOdds};
    }
}