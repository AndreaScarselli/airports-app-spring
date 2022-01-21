package com.accenture.airportsappspring.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Entity
public class Airport {

    @Id
    @CsvBindByName
    String id;

    @CsvBindByName
    String ident;

    @CsvBindByName
    String type;

    @CsvBindByName
    String name;

    @CsvBindByName(column = "latitude_deg")
    String latitudeDeg;

    @CsvBindByName(column = "longitude_deg")
    String longitudeDeg;

    @CsvBindByName(column = "elevation_ft")
    String elevationFt;

    @CsvBindByName
    String continent;

    @CsvBindByName(column = "iso_country")
    String isoCountry;

    @CsvBindByName(column = "iso_region")
    String isoRegion;

    @CsvBindByName
    String municipality;

    @CsvBindByName(column = "scheduled_service")
    String scheduledService;

    @CsvBindByName(column = "gps_code")
    String gpsCode;

    @CsvBindByName(column = "iata_code")
    String iataCode;

    @CsvBindByName(column = "local_code")
    String localCode;

    @CsvBindByName(column = "home_link")
    String homeLink;

    @CsvBindByName(column = "wikipedia_link")
    String wikipediaLink;

    @CsvBindByName
    @Column(length = 500)
    String keywords;

}
