/*
 * (c) Koninklijke Philips N.V., 2006. All rights reserved.
 */
package com.accenture.airportsappspring.repository;

import com.accenture.airportsappspring.model.Runway;
import org.springframework.data.repository.CrudRepository;

public interface RunwayRepository extends CrudRepository<Runway,String> {
}