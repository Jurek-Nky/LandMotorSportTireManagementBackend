package com.dev.reifen;

import com.dev.rennen.Rennen;
import com.dev.rennen.RennenRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReifenServiceTest {

    @Autowired
    private ReifenRepository reifenRepository;
    @Autowired
    private RennenRepository rennenRepository;
    @Autowired
    private ReifenService reifenService = new ReifenService(reifenRepository, rennenRepository);

    @BeforeEach
    void beforeEach() {
        reifenRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
    }

    @Test
    void getReifensWithoutElements() {
        Exception exception = assertThrows(RuntimeException.class, () -> reifenService.getReifen());
        String expected_message = "No Reifen were found.";
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void getReifensWithElements() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Reifen r1 = new Reifen(new Rennen(new Date(2021, 6, 2), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        reifenRepository.save(r1);
        // when
        List<Reifen> reifens_Serv = reifenService.getReifen();
        List<Reifen> reifens_Repo = (List<Reifen>) reifenRepository.findAll();
        // then
        for (int i = 0; i < reifens_Serv.size(); i++) {
            assertThat(reifens_Serv.get(i).toString()).isEqualTo(reifens_Repo.get(i).toString());
        }
    }

    @Test
    void testFindReifensByBezeichnung() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Reifen r1 = new Reifen(new Rennen(new Date(2021, 6, 2), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        reifenRepository.save(r1);
        // when
        List<Reifen> reifens = reifenService.findReifensByBezeichnung(r1.bezeichnung);
        // then
        assertThat(reifens).asList().isNotEmpty();
    }

    @Test
    @Disabled
    void testFindReifenById() {
    }

    @Test
    @Disabled
    void testFindReifenBySerialnumber() {
    }

    @Test
    @Disabled
    void testFindReifensByRennId() {
    }

    @Test
    void addNewReifen() throws ParseException {
        // given
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
        LocalDate ld = LocalDate.of(2021, 06, 03);

        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Reifen r1 = new Reifen(new Rennen(new Date(2021, 06, 02), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        // when
        reifenService.addNewReifen(r1);
        Iterable<Reifen> reifens = reifenRepository.findAll();
        ArrayList<String> reifen_strigs = new ArrayList<>();
        for (Reifen r : reifens) {
            reifen_strigs.add(r.toString());
        }
        // then
        for (String s : reifen_strigs) {
            assertThat(s).isEqualToIgnoringCase(r1.toString());
        }

    }

    @Test
    void testDeleteReifen() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Reifen r1 = new Reifen(new Rennen(new Date(2021, 6, 2), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        reifenRepository.save(r1);
        // when
        reifenService.deleteReifen(r1.reifenid);
        // then
        assertThat(reifenRepository.findById(r1.reifenid)).isNotPresent();
    }

    @Test
    @Disabled
    void testUpdateReifen() {
    }
}