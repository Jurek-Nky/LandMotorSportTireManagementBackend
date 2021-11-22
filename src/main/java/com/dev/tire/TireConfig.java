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
            Random rnd = new Random();
            String serialnumber = "abx" + rnd.nextInt(10000);
            Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021,06,03),
                    new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                    new Time(18, 30, 0), new Time(20, 0, 0));

            // tireRepository.deleteAll();
            // tireRepository.save(r1);


            tireRepository.save(r1);
            System.out.println(tireRepository.findAll());
        };
    }
}
