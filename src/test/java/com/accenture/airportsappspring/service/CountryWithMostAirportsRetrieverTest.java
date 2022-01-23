package com.accenture.airportsappspring.service;

import com.accenture.airportsappspring.repository.CountryRepository;
import com.accenture.airportsappspring.util.CountryWithNumberOfAirports;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CountryWithMostAirportsRetrieverTest {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryWithMostAirportsRetriever countryWithMostAirportsRetriever;

    @Test
    public void testShouldReturnAtMost10Elements() {
        Mockito.when(countryRepository.findCountriesWithMostAirports()).thenReturn(getMockedList());
        Assertions.assertEquals(10, countryWithMostAirportsRetriever.findCountryWithMostAirports().size());
    }

    private List<CountryWithNumberOfAirports> getMockedList() {
        List<CountryWithNumberOfAirports> list = new ArrayList<>();
        list.add(new CountryWithNumberOfAirports("", "", 1L));
        list.add(new CountryWithNumberOfAirports("", "", 2L));
        list.add(new CountryWithNumberOfAirports("", "", 3L));
        list.add(new CountryWithNumberOfAirports("", "", 4L));
        list.add(new CountryWithNumberOfAirports("", "", 5L));
        list.add(new CountryWithNumberOfAirports("", "", 6L));
        list.add(new CountryWithNumberOfAirports("", "", 7L));
        list.add(new CountryWithNumberOfAirports("", "", 8L));
        list.add(new CountryWithNumberOfAirports("", "", 9L));
        list.add(new CountryWithNumberOfAirports("", "", 10L));
        list.add(new CountryWithNumberOfAirports("", "", 11L));
        return list;
    }


}
