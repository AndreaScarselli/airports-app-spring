package com.accenture.airportsappspring.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class CountryWithNumberOfAirports {

    private String countryName;
    private String isoCountry;
    private Long numberOfAirports;

    @Override
    public String toString() {
        return "Country=" + countryName + " has " + numberOfAirports + " airports.";
    }
}
