/*
 * (c) Koninklijke Philips N.V., 2006. All rights reserved.
 */
package com.accenture.airportsappspring.repository;

import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.util.CountryWithNumberOfAirports;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CountryRepositoryTest {

    @Autowired
    CountryRepository countryRepository;

    @Test
    public void shouldReturnCountryWithCompleteCountryNameAndRandomCase() {
        List<Country> countries = countryRepository.findByNameContainingIgnoreCase("DeNmArK");
        Assertions.assertEquals(1, countries.size());
        Assertions.assertEquals("Denmark", countries.get(0).getName());
    }

    @Test
    public void shouldReturnCountryWithIncompleteCountryNameAndRandomCase() {
        List<Country> countries = countryRepository.findByNameContainingIgnoreCase("eN");
        Assertions.assertEquals(2, countries.size());
        String countryName1 = countries.get(0).getName();
        String countryName2 = countries.get(1).getName();

        Assertions.assertNotEquals(countryName1, countryName2);
        Assertions.assertTrue(countryName1.equals("Denmark") || countryName1.equals("Argentina"));
        Assertions.assertTrue(countryName2.equals("Denmark") || countryName2.equals("Argentina"));
    }

    @Test
    public void shouldReturnCountryWithCodeAndRandomCase() {
        Assertions.assertEquals("Netherlands", countryRepository.findByCodeIgnoreCase("nL").getName());
    }


    @Test
    public void testCountriesWithMostAirportsShouldReturnCorrectList() {
        List<CountryWithNumberOfAirports> countriesWithMostAirports = countryRepository.findCountriesWithMostAirports();
        Assertions.assertEquals(10, countriesWithMostAirports.size());
        Assertions.assertEquals("United States", countriesWithMostAirports.get(0).getCountryName());
        Assertions.assertEquals(4, countriesWithMostAirports.get(0).getNumberOfAirports());
        Assertions.assertEquals("Italy", countriesWithMostAirports.get(1).getCountryName());
        Assertions.assertEquals(2, countriesWithMostAirports.get(1).getNumberOfAirports());
    }
}