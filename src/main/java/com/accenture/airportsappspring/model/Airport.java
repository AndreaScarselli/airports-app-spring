package com.accenture.airportsappspring.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Entity
public class Airport {

    @Id
    @CsvBindByPosition(position = 0)
    String id;

    @CsvBindByPosition(position = 1)
    String ident;

    @CsvBindByPosition(position = 2)
    String type;

    @CsvBindByPosition(position = 3)
    String name;

    @CsvBindByPosition(position = 4)
    String latitudeDeg;

    @CsvBindByPosition(position = 5)
    String longitudeDeg;

    @CsvBindByPosition(position = 6)
    String elevationFt;

    @CsvBindByPosition(position = 7)
    String continent;

    @CsvBindByPosition(position = 8)
    String isoCountry;

    @CsvBindByPosition(position = 9)
    String isoRegion;

    @CsvBindByPosition(position = 10)
    String municipality;

    @CsvBindByPosition(position = 11)
    String scheduledService;

    @CsvBindByPosition(position = 12)
    String gpsCode;

    @CsvBindByPosition(position = 13)
    String iataCode;

    @CsvBindByPosition(position = 14)
    String localCode;

    @CsvBindByPosition(position = 15)
    String homeLink;

    @CsvBindByPosition(position = 16)
    String wikipediaLink;

    @CsvBindByPosition(position = 17)
    String keywords;

}
