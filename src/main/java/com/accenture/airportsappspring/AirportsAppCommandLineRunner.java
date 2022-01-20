package com.accenture.airportsappspring;

import com.accenture.airportsappspring.model.Airport;
import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.model.Runway;
import com.accenture.airportsappspring.repository.AirportRepository;
import com.accenture.airportsappspring.repository.CountryRepository;
import com.accenture.airportsappspring.repository.RunwayRepository;
import com.accenture.airportsappspring.util.CsvReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportsAppCommandLineRunner implements CommandLineRunner {

    @Value("${csv-path.countries}")
    private String countriesCsvPath;

    @Value("${csv-path.runways}")
    private String runwaysCsvPath;

    @Value("${csv-path.airports}")
    private String airportsCsvPath;

    private final CsvReader csvReader;

    private final AirportRepository airportRepository;
    private final CountryRepository countryRepository;
    private final RunwayRepository runwayRepository;

    @Autowired
    public AirportsAppCommandLineRunner(CsvReader csvReader, AirportRepository airportRepository, CountryRepository countryRepository, RunwayRepository runwayRepository) {
        this.csvReader = csvReader;
        this.airportRepository = airportRepository;
        this.countryRepository = countryRepository;
        this.runwayRepository = runwayRepository;
    }

    private static Logger LOG = LoggerFactory.getLogger(AirportsAppCommandLineRunner.class);

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Reading CSVs.");
        List<Airport> airports = csvReader.readCsv(airportsCsvPath, Airport.class);
        List<Runway> runways = csvReader.readCsv(runwaysCsvPath, Runway.class);
        List<Country> countries = csvReader.readCsv(countriesCsvPath, Country.class);

        LOG.info("Saving entities in the DB.");
        airportRepository.saveAll(airports);
        runwayRepository.saveAll(runways);
        countryRepository.saveAll(countries);

        LOG.info("Saving Entities - Done!");
    }


}
