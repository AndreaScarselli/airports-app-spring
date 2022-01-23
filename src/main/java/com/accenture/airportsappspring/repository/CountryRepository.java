/*
 * (c) Koninklijke Philips N.V., 2006. All rights reserved.
 */
package com.accenture.airportsappspring.repository;

import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.util.CountryWithNumberOfAirports;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country,String> {

    List<Country> findByNameContainingIgnoreCase(String name);

    Country findByCodeIgnoreCase(String code);

    @Query("select new com.accenture.airportsappspring.util.CountryWithNumberOfAirports(c.name, a.isoCountry, count(a.isoCountry)) \n" +
            "from Airport a, Country c\n" +
            "where a.isoCountry = c.code\n" +
            "group by a.isoCountry, c.name\n" +
            "order by count(a.isoCountry) desc")
    List<CountryWithNumberOfAirports> findCountriesWithMostAirports();
}