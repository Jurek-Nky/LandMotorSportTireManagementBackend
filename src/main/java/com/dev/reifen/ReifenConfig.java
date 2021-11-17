package com.dev.reifen;

import com.dev.rennen.Rennen;
import com.dev.rennen.RennenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Random;

@Configuration
// ReifenConfig is just used to configure the database. At the moment it fills the database with some example entries.
public class ReifenConfig {

    @Bean
    CommandLineRunner commandLineRunner(ReifenRepository reifenRepository) {
        return args -> {
            Random rnd = new Random();
            String serialnumber = "abx" + rnd.nextInt(10000);
            Reifen r1 = new Reifen(new Rennen(new Date(2021, 06, 02), "eifel"), serialnumber, "cooler reifen", new Date(2021, 6, 2),
                    new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                    new Time(18, 30, 0), new Time(20, 0, 0));
//            Reifen r2 = new Reifen("298(6ns)", "wert3614ljoi", new Date(2021, 6, 2),
//                    new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
//                    new Time(18, 30, 0), new Time(20, 0, 0));
//            Reifen r3 = new Reifen("298(6ns)", "yxv5726mjkoi", new Date(2021, 6, 2),
//                    new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
//                    new Time(18, 30, 0), new Time(20, 0, 0));
//            Reifen r4 = new Reifen("201", "sfrw2976znoi", new Date(2021, 6, 2),
//                    new Time(17, 50, 0), "med G/D", "", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
//                    new Time(13, 0, 0), new Time(14, 30, 0));
//
            reifenRepository.save(r1);
        };
    }
}
