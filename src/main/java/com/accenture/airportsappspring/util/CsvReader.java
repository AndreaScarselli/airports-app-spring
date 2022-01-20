package com.accenture.airportsappspring.util;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvReader {

    @SuppressWarnings("unchecked")
    public static <T> List<T> readCsv(String fileName, Class<T> tClass) throws IOException {
        HeaderColumnNameMappingStrategy<T> headerColumnNameMappingStrategy = new HeaderColumnNameMappingStrategy<>();
        headerColumnNameMappingStrategy.setType(tClass);
        Path path = Paths.get(fileName);

        Reader reader = Files.newBufferedReader(path);
        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withType(tClass)
                .withMappingStrategy(headerColumnNameMappingStrategy)
                .build();

        return csvToBean.parse();
    }
}
