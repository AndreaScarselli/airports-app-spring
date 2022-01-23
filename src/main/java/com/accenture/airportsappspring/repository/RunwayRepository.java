/*
 * (c) Koninklijke Philips N.V., 2006. All rights reserved.
 */
package com.accenture.airportsappspring.repository;

import com.accenture.airportsappspring.model.Runway;
import com.accenture.airportsappspring.util.AirportWithRunway;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RunwayRepository extends CrudRepository<Runway,String> {

    List<Runway> findAllByAirportRef(String airportRef);

    @Query("select new com.accenture.airportsappspring.util.AirportWithRunway(a.name, r.id) from Airport a, Runway r where a.isoCountry=?1 AND r.airportRef = a.id")
    List<AirportWithRunway> findAllRunwaysInACountry(String countryCode);
}