package com.dev.reifen;

import com.dev.rennen.Rennen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ReifenRepositoryTest {
    @Autowired
    ReifenRepository reifenRepository;

    @BeforeEach
    void beforeEach() {
        reifenRepository.deleteAll();
    }

    @Test
    void findReifensByBezeichnung() {
        //given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Reifen r1 = new Reifen(new Rennen(new Date(2021, 6, 2), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        reifenRepository.save(r1);
        //when
        Iterable<Reifen> reifens = reifenRepository.findReifensByBezeichnung(r1.bezeichnung);
        //then
        assertThat(reifens).asList().isNotEmpty();
    }

    @Test
    void findReifenByReifenid() {
        //given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Reifen r1 = new Reifen(new Rennen(new Date(2021, 6, 2), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        reifenRepository.save(r1);
        //when
        Optional<Reifen> reifen = reifenRepository.findReifenByReifenid(r1.reifenid);
        //then
        assertThat(reifen).isPresent();
    }

    @Test
    void findReifenBySerialNumber() {
        //given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Reifen r1 = new Reifen(new Rennen(new Date(2021, 6, 2), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        reifenRepository.save(r1);
        //when
        Optional<Reifen> reifen = reifenRepository.findReifenBySerialNumber(r1.serialNumber);
        //then
        assertThat(reifen).isPresent();
    }

    @Test
    void findReifenByRennen_Rennid() {
        //given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Reifen r1 = new Reifen(new Rennen(new Date(2021, 6, 2), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        reifenRepository.save(r1);
        //when
        Iterable<Reifen> reifens = reifenRepository.findReifensByRennen_Rennid(r1.rennen.getRennid());
        //then
        assertThat(reifens).asList().isNotEmpty();
    }
}