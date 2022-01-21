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

    private static final int COUNTRY_CODE_LENGTH = 2;

    private final AirportRepository airportRepository;
    private final CountryRepository countryRepository;
    private final RunwayRepository runwayRepository;

    @Autowired
    public RunwaysRetriever(AirportRepository airportRepository, CountryRepository countryRepository, RunwayRepository runwayRepository) {
        this.airportRepository = airportRepository;
        this.countryRepository = countryRepository;
        this.runwayRepository = runwayRepository;
    }

    // return a Map<Country, Map<Airport, List<Runways>>>
    public Map<Country, Map<Airport, List<Runway>>> searchRunwaysByCountry(String countryName) {
        Map<Country, Map<Airport, List<Runway>>> runwaysPerCountry = new HashMap<>();
        List<Country> matchingCountries = new ArrayList<>();

        Country matchingCountry = populateCountriesWhenACountryCodeIsReceived(countryName);
        if(matchingCountry != null) {
            matchingCountries.add(matchingCountry);
        } else {
            matchingCountries = convertCountryNameToCountryCode(countryName);

            if (matchingCountries == null || matchingCountries.isEmpty()) {
                // it means it is not possible to associate the string tu any country
                return runwaysPerCountry;
            }
        }

        for (Country country : matchingCountries) {
            Map<Airport, List<Runway>> runwaysPerAirport = new HashMap<>();
            List<Airport> airports = findAirportsInACountry(country.getCode());
            for (Airport airport : airports) {
                // discard airports without runways
                List<Runway> runwaysInTheAirport = findRunwaysInAnAirport(airport);
                if (!runwaysInTheAirport.isEmpty()) {
                    runwaysPerAirport.put(airport, runwaysInTheAirport);
                }
            }
            runwaysPerCountry.put(country, runwaysPerAirport);
        }

        return runwaysPerCountry;
    }

    private Country populateCountriesWhenACountryCodeIsReceived(String countryCode) {
        return countryRepository.findByCodeIgnoreCase(countryCode);
    }

    private List<Runway> findRunwaysInAnAirport(Airport airport) {
        return runwayRepository.findAllByAirportIdent(airport.getIdent());
    }

    private List<Airport> findAirportsInACountry(String country) {
        return airportRepository.findAllByIsoCountryIgnoreCase(country);
    }

    private List<Country> convertCountryNameToCountryCode(String countryName) {
        List<Country> countries = countryRepository.findByNameContainingIgnoreCase(countryName);
        if (countries.size() != 0) {
            return countries;
        }
        return null;
    }
}
