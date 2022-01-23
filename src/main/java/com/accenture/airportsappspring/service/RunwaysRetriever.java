package com.accenture.airportsappspring.service;

import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.repository.AirportRepository;
import com.accenture.airportsappspring.repository.CountryRepository;
import com.accenture.airportsappspring.repository.RunwayRepository;
import com.accenture.airportsappspring.util.AirportWithRunway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RunwaysRetriever {

    private final AirportRepository airportRepository;
    private final CountryRepository countryRepository;
    private final RunwayRepository runwayRepository;

    @Autowired
    public RunwaysRetriever(AirportRepository airportRepository, CountryRepository countryRepository, RunwayRepository runwayRepository) {
        this.airportRepository = airportRepository;
        this.countryRepository = countryRepository;
        this.runwayRepository = runwayRepository;
    }

    public Map<Country, Map<String, List<String>>> searchRunwaysByCountry(String countryName) {
        List<Country> matchingCountries =  getMatchingCountries(countryName);
        return getRunwaysInMatchingCountries(matchingCountries);
    }

    private Map<Country, Map<String, List<String>>> getRunwaysInMatchingCountries(List<Country> matchingCountries) {
        Map<Country, Map<String, List<String>>> runwaysInMatchingCountries = new HashMap<>();
        for (Country country : matchingCountries) {
            runwaysInMatchingCountries.put(country, getRunwaysForAllTheAirportsInACountry(country));
        }
        return runwaysInMatchingCountries;
    }

    private Map<String, List<String>> getRunwaysForAllTheAirportsInACountry(Country country) {
        Map<String, List<String>> runwaysInACountry = new HashMap<>();
        List<AirportWithRunway> airportWithRunwayList = runwayRepository.findAllRunwaysInACountry(country.getCode());
        for (AirportWithRunway airportWithRunway : airportWithRunwayList) {
            List<String> runwaysInAnAirport = runwaysInACountry.get(airportWithRunway.getAirportName());
            // discard airports without runways
            if(runwaysInAnAirport == null) {
                runwaysInAnAirport = new ArrayList<>();
            }
            runwaysInAnAirport.add(airportWithRunway.getRunwayId());
            runwaysInACountry.put(airportWithRunway.getAirportName(), runwaysInAnAirport);
        }
        return runwaysInACountry;
    }

    public List<Country> getMatchingCountries(String countryName) {
        List<Country> matchingCountries = new ArrayList<>();

        Country matchingCountry = getCountryFromACountryCode(countryName);
        if(matchingCountry != null) {
            matchingCountries.add(matchingCountry);
        } else {
            matchingCountries = getCountriesFromCountryName(countryName);
        }

        return matchingCountries;
    }

    private Country getCountryFromACountryCode(String countryCode) {
        return countryRepository.findByCodeIgnoreCase(countryCode);
    }

    private List<Country> getCountriesFromCountryName(String countryName) {
        return countryRepository.findByNameContainingIgnoreCase(countryName);
    }
}
