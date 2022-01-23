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
        Assertions.assertEquals(2, runwayRepository.findAllByAirportIdent("00ID").size());
    }

    @Test
    public void testShouldReturnNoRunwaysWithWrongIdent() {
        Assertions.assertEquals(0, runwayRepository.findAllByAirportIdent("00AA").size());
    }
}