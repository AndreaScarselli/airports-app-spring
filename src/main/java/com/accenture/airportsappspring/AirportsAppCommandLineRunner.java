package com.accenture.airportsappspring;

import com.accenture.airportsappspring.model.Country;
import com.accenture.airportsappspring.service.CountryWithMostAirportsRetriever;
import com.accenture.airportsappspring.service.RunwaysRetriever;
import com.accenture.airportsappspring.util.CountryWithNumberOfAirports;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
@Profile("prod")
public class AirportsAppCommandLineRunner implements CommandLineRunner {

    private final CountryWithMostAirportsRetriever countryWithMostAirportsRetriever;
    private final RunwaysRetriever runwaysRetriever;

    public AirportsAppCommandLineRunner(RunwaysRetriever runwaysRetriever, CountryWithMostAirportsRetriever countryWithMostAirportsRetriever) {
        this.runwaysRetriever = runwaysRetriever;
        this.countryWithMostAirportsRetriever = countryWithMostAirportsRetriever;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Enter country or country code: \n");
            Scanner scanner = new Scanner(System.in);
            String country = scanner.nextLine();

            retrieveRunwaysPerCountryAndPrint(country);

            List<CountryWithNumberOfAirports> resultClasses = countryWithMostAirportsRetriever.findCountryWithMostAirports();
            resultClasses.forEach(System.out::println);
        }
    }

    private void retrieveRunwaysPerCountryAndPrint(String countryName) {
        Map<Country, Map<String, List<String>>> runwaysPerCountry = runwaysRetriever.searchRunwaysByCountry(countryName);

        StringBuilder s = new StringBuilder();
        for(Country country: runwaysPerCountry.keySet()) {
            s.append("---Country " + country.getName() + ": \n");
            Map<String, List<String>> runwaysPerAirport = runwaysPerCountry.get(country);
            for (String airport : runwaysPerAirport.keySet()) {
                s.append(airport).append(": ");
                for (String runway : runwaysPerAirport.get(airport)) {
                    s.append(runway + ", ");
                }
                s.append("\n");
            }
        }
        if(s.toString().equals("")) {
            s.append("There are no runways information for the specified country.");
        }
        System.out.println(s);
    }

}
