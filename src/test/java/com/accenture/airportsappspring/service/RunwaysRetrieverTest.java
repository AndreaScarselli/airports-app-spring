package com.accenture.airportsappspring.service;

import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.repository.CountryRepository;
import com.accenture.airportsappspring.repository.RunwayRepository;
import com.accenture.airportsappspring.util.AirportWithRunway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class RunwaysRetrieverTest {

    @Mock
    CountryRepository countryRepository;
    @Mock
    RunwayRepository runwayRepository;

    @InjectMocks
    RunwaysRetriever runwaysRetriever;

    @Test
    public void shouldGetAMapWith2CountriesIfPartialNameMatch2Countries() {
        List<Country> countries= new ArrayList<>();
        Country country1 = new Country();
        Country country2 = new Country();
        country1.setCode("c1");
        country2.setCode("c2");
        countries.add(country1);
        countries.add(country2);
        List<AirportWithRunway> airportWithRunwayList1 = new ArrayList<>();
        AirportWithRunway airportWithRunway1 = new AirportWithRunway("c1", "r1");
        airportWithRunwayList1.add(airportWithRunway1);
        List<AirportWithRunway> airportWithRunwayList2 = new ArrayList<>();
        AirportWithRunway airportWithRunway2 = new AirportWithRunway("c2", "r2");
        airportWithRunwayList2.add(airportWithRunway2);
        Mockito.when(countryRepository.findByCodeIgnoreCase(Mockito.anyString())).thenReturn(null);
        Mockito.when(countryRepository.findByNameContainingIgnoreCase(Mockito.anyString())).thenReturn(countries);
        Mockito.when(runwayRepository.findAllRunwaysInACountry("c1")).thenReturn(airportWithRunwayList1);
        Mockito.when(runwayRepository.findAllRunwaysInACountry("c2")).thenReturn(airportWithRunwayList2);
        Map<Country, Map<String, List<String>>> result = runwaysRetriever.searchRunwaysByCountry("c");
        Assertions.assertEquals(2, result.keySet().size());
        Assertions.assertEquals(1, result.get(country1).keySet().size());
        Assertions.assertEquals(1, result.get(country1).get("c1").size());
        Assertions.assertEquals("r1", result.get(country1).get("c1").get(0));
        Assertions.assertEquals(1, result.get(country2).keySet().size());
        Assertions.assertEquals(1, result.get(country2).get("c2").size());
        Assertions.assertEquals("r2", result.get(country2).get("c2").get(0));
    }


    @Test
    public void shouldReturnEmptyMapIfNoCountryCodeIsRetrieved() {
        Mockito.when(countryRepository.findByCodeIgnoreCase(Mockito.anyString())).thenReturn(null);
        Mockito.when(countryRepository.findByNameContainingIgnoreCase(Mockito.anyString())).thenReturn(new ArrayList<>());
        Map<Country, Map<String, List<String>>> result = runwaysRetriever.searchRunwaysByCountry("c");
        Assertions.assertEquals(0, result.keySet().size());
    }

}
