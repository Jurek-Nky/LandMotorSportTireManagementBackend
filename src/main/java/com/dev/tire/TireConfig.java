package com.dev.tire;

import com.dev.race.Race;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Random;

@Configuration
// ReifenConfig is just used to configure the database. At the moment it fills the database with some example entries.
public class TireConfig {

    @Bean
    CommandLineRunner commandLineRunner(TireRepository tireRepository) {
        return args -> {
        };
    }
}
