package com.example.livebetting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OddsGeneratorServiceTest {

    @Autowired
    private OddsGeneratorService oddsGeneratorService;


    @Test
    void generateValidOdds_TargetSumIsCorrect() {
        double[] odds = oddsGeneratorService.generateValidOdds();
        double sum = odds[0] + odds[1] + odds[2];
        assertEquals(9.0, sum, 0.0001, "The sum of the odds should equal 9.0");
    }

    @Test
    void generateValidOdds_OddsWithinBounds() {
        double[] odds = oddsGeneratorService.generateValidOdds();
        for (double odd : odds) {
            assertTrue(odd >= 1.01 && odd <= 9.99, "Each odd should be within [1.01, 9.99]");
        }
    }

    @Test
    void generateValidOdds_ArrayLengthIsThree() {
        double[] odds = oddsGeneratorService.generateValidOdds();
        assertEquals(3, odds.length, "The array should have exactly 3 elements");
    }
}