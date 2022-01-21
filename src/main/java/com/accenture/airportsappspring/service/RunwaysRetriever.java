package com.accenture.airportsappspring.service;

import com.accenture.airportsappspring.model.Airport;
import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.model.Runway;
import com.accenture.airportsappspring.repository.AirportRepository;
import com.accenture.airportsappspring.repository.CountryRepository;
import com.accenture.airportsappspring.repository.RunwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    public void searchRunwaysByCountry() {
        Map<String, List<Runway>> runwaysPerAirport = new HashMap<>();
        System.out.println("Enter country or country code: \n");
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        if(country.length() > COUNTRY_CODE_LENGTH) {
            country = convertCountryNameToCountryCode(country);
        }

        List<Airport> airports = findAirportsInACountry(country);

        for(Airport airport: airports) {
            runwaysPerAirport.put(airport.getName(), findRunwaysInAnAirport(airport));
        }
    }

    private List<Runway> findRunwaysInAnAirport(Airport airport) {
        return runwayRepository.findAllByAirportIdent(airport.getIdent());
    }

    private List<Airport> findAirportsInACountry(String country) {
        return airportRepository.findAllByIsoCountryIgnoreCase(country);
    }

    private String convertCountryNameToCountryCode(String countryName) {
        Country country = countryRepository.findByNameIgnoreCase(countryName);
        if(country != null) {
            return country.getCode();
        }
        return null;
    }
}
