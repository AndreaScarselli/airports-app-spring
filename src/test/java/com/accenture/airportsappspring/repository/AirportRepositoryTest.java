package com.accenture.airportsappspring.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AirportRepositoryTest {

    @Autowired
    AirportRepository airportRepository;

    @Test
    public void testShouldReturnNumberOfCountriesSearchingByIso() {
        Assertions.assertEquals(4, airportRepository.findAllByIsoCountryIgnoreCase("US").size());
    }

}

