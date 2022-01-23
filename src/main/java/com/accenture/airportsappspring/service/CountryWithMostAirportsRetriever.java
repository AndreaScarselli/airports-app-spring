package com.accenture.airportsappspring.service;

import com.accenture.airportsappspring.repository.CountryRepository;
import com.accenture.airportsappspring.util.CountryWithNumberOfAirports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryWithMostAirportsRetriever {

    public static final int TOP_N_COUNTRIES = 10;
    private final CountryRepository countryRepository;

    @Autowired
    public CountryWithMostAirportsRetriever(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryWithNumberOfAirports> findCountryWithMostAirports() {
        return countryRepository.findCountriesWithMostAirports().stream().limit(TOP_N_COUNTRIES).collect(Collectors.toList());
    }

}
