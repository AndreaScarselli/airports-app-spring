package com.accenture.airportsappspring.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @AllArgsConstructor @ToString
public class CountryWithNumberOfAirports {

    private String countryName;
    private String isoCountry;
    private Long numberOfAirports;
}
