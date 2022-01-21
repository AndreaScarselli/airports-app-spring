package com.accenture.airportsappspring.model;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Entity
public class Country {

    @Id
    @CsvBindByName
    String id;

    @CsvBindByName
    String code;

    @CsvBindByName
    String name;

    @CsvBindByName
    String continent;

    @CsvBindByName(column = "wikipedia_link")
    String wikipediaLink;

    @CsvBindByName
    String keywords;
}
