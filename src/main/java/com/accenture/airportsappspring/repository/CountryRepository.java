/*
 * (c) Koninklijke Philips N.V., 2006. All rights reserved.
 */
package com.accenture.airportsappspring.repository;

import com.accenture.airportsappspring.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country,String> {

    public Country findByNameIgnoreCase(String name);
}