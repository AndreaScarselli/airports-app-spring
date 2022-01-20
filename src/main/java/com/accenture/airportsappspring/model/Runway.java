package com.accenture.airportsappspring.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @Entity
public class Runway {

    @Id
    @CsvBindByPosition(position = 0)
    String id;

    @CsvBindByPosition(position = 1)
    String airportRef;

    @CsvBindByPosition(position = 2)
    String airportIdent;

    @CsvBindByPosition(position = 3)
    String lengthFt;

    @CsvBindByPosition(position = 4)
    String widthFt;

    @CsvBindByPosition(position = 5)
    String surface;

    @CsvBindByPosition(position = 6)
    String lighted;

    @CsvBindByPosition(position = 7)
    String closed;

    @CsvBindByPosition(position = 8)
    String leIdent;

    @CsvBindByPosition(position = 9)
    String leLatitudeDeg;

    @CsvBindByPosition(position = 10)
    String leLongitudeDeg;

    @CsvBindByPosition(position = 11)
    String leElevationFt;

    @CsvBindByPosition(position = 12)
    String leHeadingDegT;

    @CsvBindByPosition(position = 13)
    String leDisplacedThresholdFt;

    @CsvBindByPosition(position = 14)
    String heIent;

    @CsvBindByPosition(position = 15)
    String heLatitudeDeg;

    @CsvBindByPosition(position = 16)
    String heLongitudeDeg;

    @CsvBindByPosition(position = 17)
    String heElevationFt;

    @CsvBindByPosition(position = 18)
    String heHeadingDegT;

    @CsvBindByPosition(position = 19)
    String heDisplacedThresholdFt;

}
