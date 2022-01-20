package com.accenture.airportsappspring.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Entity
public class Country {

    @Id
    @CsvBindByPosition(position = 0)
    String id;

    @CsvBindByPosition(position = 1)
    String code;

    @CsvBindByPosition(position = 2)
    String name;

    @CsvBindByPosition(position = 3)
    String continent;

    @CsvBindByPosition(position = 4)
    String wikipediaLink;

    @CsvBindByPosition(position = 5)
    String keywords;
}
