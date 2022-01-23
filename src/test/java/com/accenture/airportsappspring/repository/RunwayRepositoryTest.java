/*
 * (c) Koninklijke Philips N.V., 2006. All rights reserved.
 */
package com.accenture.airportsappspring.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RunwayRepositoryTest {

    @Autowired
    RunwayRepository runwayRepository;

    @Test
    public void testShouldReturnRunwaysByAirportIdent() {
        Assertions.assertEquals(2, runwayRepository.findAllByAirportRef("6537").size());
    }

    @Test
    public void testShouldReturnNoRunwaysWithWrongIdent() {
        Assertions.assertEquals(0, runwayRepository.findAllByAirportRef("653777").size());
    }
}