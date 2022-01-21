package com.accenture.airportsappspring.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Entity
public class Runway {

    @Id
    @CsvBindByName
    String id;

    @CsvBindByName(column = "airport_ref")
    String airportRef;

    @CsvBindByName(column = "airport_ident")
    String airportIdent;

    @CsvBindByName(column = "length_ft")
    String lengthFt;

    @CsvBindByName(column = "width_ft")
    String widthFt;

    @CsvBindByName
    String surface;

    @CsvBindByName
    String lighted;

    @CsvBindByName
    String closed;

    @CsvBindByName(column = "le_ident")
    String leIdent;

    @CsvBindByName(column = "le_latitude_deg")
    String leLatitudeDeg;

    @CsvBindByName(column = "le_longitude_deg")
    String leLongitudeDeg;

    @CsvBindByName(column = "le_elevation_ft")
    String leElevationFt;

    @CsvBindByName(column = "le_heading_degT")
    String leHeadingDegT;

    @CsvBindByName(column = "le_displaced_threshold_ft")
    String leDisplacedThresholdFt;

    @CsvBindByName(column = "he_ident")
    String heIdent;

    @CsvBindByName(column = "he_latitude_deg")
    String heLatitudeDeg;

    @CsvBindByName(column = "he_longitude_deg")
    String heLongitudeDeg;

    @CsvBindByName(column = "he_elevation_ft")
    String heElevationFt;

    @CsvBindByName(column = "he_heading_degT")
    String heHeadingDegT;

    @CsvBindByName(column = "he_displaced_threshold_ft")
    String heDisplacedThresholdFt;

}
