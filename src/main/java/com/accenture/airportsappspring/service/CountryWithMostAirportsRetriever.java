package com.accenture.airportsappspring.service;

import com.accenture.airportsappspring.repository.AirportRepository;
import com.accenture.airportsappspring.util.CountryWithNumberOfAirports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryWithMostAirportsRetriever {

    public static final int TOP_N_COUNTRIES = 10;
    private final AirportRepository airportRepository;

    @Autowired
    public CountryWithMostAirportsRetriever(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<CountryWithNumberOfAirports> findCountryWithMostAirports() {
        return airportRepository.findCountriesWithMostAirports().stream().limit(TOP_N_COUNTRIES).collect(Collectors.toList());
    }

}
