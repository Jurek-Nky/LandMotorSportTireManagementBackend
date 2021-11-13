package com.dev.reifen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Configuration
// ReifenConfig is just used to configure the database. At the moment it fills the database with some example entries.
public class ReifenConfig {

    @Bean
    CommandLineRunner commandLineRunner(ReifenRepository repository) {
        return args -> {
            Reifen r1 = new Reifen("298(6ns)", new Date(2021, 6, 2), new Time(0), "med G/D", "Q2", new double[]{1.49, 1.33, 1.52, 1.37}, 20, 90, 90, new Time(18, 30, 0), new Time(20, 0, 0));            ;
            Reifen r2 = new Reifen("298(6ns)", new Date(2021, 6, 2), new Time(0), "med G/D", "Q2", new double[]{1.49, 1.33, 1.52, 1.37}, 20, 90, 90, new Time(18, 30, 0), new Time(20, 0, 0));
            Reifen r3 = new Reifen("298(6ns)", new Date(2021, 6, 2), new Time(0), "med G/D", "Q2", new double[]{1.49, 1.33, 1.52, 1.37}, 20, 90, 90, new Time(18, 30, 0), new Time(20, 0, 0));
            repository.deleteAll();
            repository.saveAll(List.of(r1, r3, r2));
        };
    }
}
