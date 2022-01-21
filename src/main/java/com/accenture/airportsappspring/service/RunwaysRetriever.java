package com.accenture.airportsappspring.service;

import com.accenture.airportsappspring.model.Airport;
import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.model.Runway;
import com.accenture.airportsappspring.repository.AirportRepository;
import com.accenture.airportsappspring.repository.CountryRepository;
import com.accenture.airportsappspring.repository.RunwayRepository;
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

    public Map<Country, Map<Airport, List<Runway>>> searchRunwaysByCountry(String countryName) {
        List<Country> matchingCountries =  getMatchingCountries(countryName);
        return getRunwaysInMatchingCountries(matchingCountries);
    }

    private Map<Country, Map<Airport, List<Runway>>> getRunwaysInMatchingCountries(List<Country> matchingCountries) {
        Map<Country, Map<Airport, List<Runway>>> runwaysInMatchingCountries = new HashMap<>();
        for (Country country : matchingCountries) {
            runwaysInMatchingCountries.put(country, getRunwaysForAllTheAirportsInACountry(country));
        }
        return runwaysInMatchingCountries;
    }

    private Map<Airport, List<Runway>> getRunwaysForAllTheAirportsInACountry(Country country) {
        List<Airport> airports = getAirportsInACountry(country.getCode());
        Map<Airport, List<Runway>> runwaysInACountry = new HashMap<>();
        //for each airport in the country
        for (Airport airport : airports) {
            List<Runway> runwaysInAnAirport = getRunwaysInAnAirport(airport);
            // discard airports without runways
            if(!runwaysInAnAirport.isEmpty()) {
                runwaysInACountry.put(airport, getRunwaysInAnAirport(airport));
            }
        }
        return runwaysInACountry;
    }

    private List<Runway> getRunwaysInAnAirport(Airport airport) {
        return runwayRepository.findAllByAirportIdent(airport.getIdent());
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

    private List<Airport> getAirportsInACountry(String country) {
        return airportRepository.findAllByIsoCountryIgnoreCase(country);
    }

    private List<Country> getCountriesFromCountryName(String countryName) {
        return countryRepository.findByNameContainingIgnoreCase(countryName);
    }
}
