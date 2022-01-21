package com.accenture.airportsappspring.util;

import com.accenture.airportsappspring.model.Airport;
import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.model.Runway;
import com.accenture.airportsappspring.repository.AirportRepository;
import com.accenture.airportsappspring.repository.CountryRepository;
import com.accenture.airportsappspring.repository.RunwayRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvReader {

    @Value("${csv-path.countries}")
    private String countriesCsvPath;

    @Value("${csv-path.runways}")
    private String runwaysCsvPath;

    @Value("${csv-path.airports}")
    private String airportsCsvPath;

    private final ResourceLoader resourceLoader;
    private final AirportRepository airportRepository;
    private final CountryRepository countryRepository;
    private final RunwayRepository runwayRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CsvReader.class);

    public CsvReader(ResourceLoader resourceLoader,
                     AirportRepository airportRepository,
                     CountryRepository countryRepository,
                     RunwayRepository runwayRepository) {
        this.resourceLoader = resourceLoader;
        this.airportRepository = airportRepository;
        this.countryRepository = countryRepository;
        this.runwayRepository = runwayRepository;
    }

    @PostConstruct
    private void initializeDataBase() throws IOException {
        LOG.info("Reading CSVs.");
        List<Airport> airports = readCsv(airportsCsvPath, Airport.class);
        List<Runway> runways = readCsv(runwaysCsvPath, Runway.class);
        List<Country> countries = readCsv(countriesCsvPath, Country.class);

        LOG.info("Saving entities in the DB.");
        airportRepository.saveAll(airports);
        runwayRepository.saveAll(runways);
        countryRepository.saveAll(countries);
        LOG.info("Saving Entities - Done!");
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> readCsv(String fileName, Class<T> tClass) throws IOException {
        HeaderColumnNameMappingStrategy<T> headerColumnNameMappingStrategy = new HeaderColumnNameMappingStrategy<>();
        headerColumnNameMappingStrategy.setType(tClass);

        Resource resource = resourceLoader.getResource(fileName);
        Path path = Paths.get(resource.getURI());
        Reader reader = Files.newBufferedReader(path);

        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withType(tClass)
                .withMappingStrategy(headerColumnNameMappingStrategy)
                .build();

        return csvToBean.parse();
    }
}
