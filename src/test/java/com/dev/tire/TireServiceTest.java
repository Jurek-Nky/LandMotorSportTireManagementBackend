package com.dev.tire;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TireServiceTest {

    @Autowired
    private TireRepository tireRepository;
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private TireService tireService = new TireService(tireRepository, raceRepository);

    @BeforeEach
    void beforeEach() {
        tireRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
    }

    @Test
    void getTiresWithoutElements() {
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.getTires());
        String expected_message = "No tires were found.";
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void getTiresWithElements() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        tireRepository.save(r1);
        // when
        List<Tire> reifens_Serv = tireService.getTires();
        List<Tire> reifens_Repo = (List<Tire>) tireRepository.findAll();
        // then
        for (int i = 0; i < reifens_Serv.size(); i++) {
            assertThat(reifens_Serv.get(i).toString()).isEqualTo(reifens_Repo.get(i).toString());
        }
    }

    @Test
    void testFindTiresByBezeichnungWithElement() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        tireRepository.save(r1);
        // when
        List<Tire> tires = tireService.findTiresByBezeichnung(r1.bezeichnung);
        // then
        assertThat(tires).asList().isNotEmpty();
    }

    @Test
    void testFindTiresByBezeichnungWithoutElement() {
        String bez = "lalala";
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTiresByBezeichnung(bez));
        String expected_message = String.format("No tires were found with Bezeichnung: %s", bez);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testFindTireByIdWithElement() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        tireRepository.save(r1);
        // when
        Optional<Tire> reifens = tireService.findTireById(r1.tireID);
        // then
        assertThat(reifens).isPresent();
    }

    @Test
    void testFindTireByIdWithoutElement() {
        Long id = 316426946l;
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTireById(id));
        String expected_message = String.format("No tires were found with TireID: %s", id);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testFindTireBySerialnumberWithElement() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        tireRepository.save(r1);
        // when
        Optional<Tire> reifens = tireService.findTireBySerialnumber(r1.serialNumber);
        // then
        assertThat(reifens).isPresent();
    }

    @Test
    void testFindTireBySerialnumberWithoutElement() {
        String serial = "lalala";
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTireBySerialnumber(serial));
        String expected_message = String.format("No tires were found with Serialnumber: %s", serial);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testFindTiresByRennIdWithElement() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        tireRepository.save(r1);
        // when
        List<Tire> tires = tireService.findTiresByRennId(r1.race.getRaceID());
        // then
        assertThat(tires).asList().isNotEmpty();
    }

    @Test
    void testFindTiresByRennIdWithoutElement() {
        Long rennid = 631452l;
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTiresByRennId(rennid));
        String expected_message = String.format("No tires were found with RennID: %s", rennid);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void addNewTire() throws ParseException {
        // given
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
        LocalDate ld = LocalDate.of(2021, 06, 03);

        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        // when
        tireService.addNewTire(r1);
        Iterable<Tire> reifens = tireRepository.findAll();
        ArrayList<String> reifen_strigs = new ArrayList<>();
        for (Tire r : reifens) {
            reifen_strigs.add(r.toString());
        }
        // then
        for (String s : reifen_strigs) {
            assertThat(s).isEqualToIgnoringCase(r1.toString());
        }

    }

    @Test
    void testDeleteTireWithElement() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"), serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        tireRepository.save(r1);
        // when
        tireService.deleteTire(r1.tireID);
        // then
        assertThat(tireRepository.findById(r1.tireID)).isNotPresent();
    }

    @Test
    void testDeleteTireWithoutElement() {
        Long id = 63649l;
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.deleteTire(id));
        String expected_message = String.format("tire with id %s does not exist.", id);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testUpdateTireForExceptionNoTireFound() {
        //given
        // when
        Exception exception = assertThrows(RuntimeException.class,
                () -> tireService.updateTire(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
        String expected_message = String.format("Tire with id %s could not be found.", null);
        //then
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testUpdateTireForExceptionNoRennenFound() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"),
                serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        tireRepository.save(r1);
        // when
        Long wrongRennId = 142l;
        Tire r2 = new Tire(r1.tireID, r1.race, "serial", "bezeich", LocalDate.of(2011, 11, 11),
                new Time(11, 11, 11), "spezifi", "session", 1.1, 1.2, 1.3, 1.4, 1, 2, 3, new Time(12, 12, 12),
                new Time(13, 13, 13), 2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 3.1, 3.2, 3.3, 3.4, "abgeb");
        Exception exception = assertThrows(RuntimeException.class,
                () -> tireService.updateTire(r2.tireID, r2.bezeichnung, r2.datum, Optional.of(r2.tp_hot1),
                Optional.of(r2.tp_hot2), Optional.of(r2.tp_hot3), Optional.of(r2.tp_hot4), Optional.of(r2.bleed_hot1),
                Optional.of(r2.bleed_hot2), Optional.of(r2.bleed_hot3), Optional.of(r2.bleed_hot4),
                Optional.of(r2.bleed_in_blanket), r2.abgegeben_fuer, r2.heatingStart, r2.heatingStop,
                Optional.of(r2.heatingTemp), Optional.of(r2.heatingTime), Optional.of(r2.kaltdruck1),
                Optional.of(r2.kaltdruck2), Optional.of(r2.kaltdruck3), Optional.of(r2.kaltdruck4),
                Optional.of(r2.kaltdruckTemp), r2.serialNumber, r2.spez, Optional.of(r2.target), r2.uhrzeit,
                wrongRennId));
        String expected_message = String.format("Rennen with id %s not found.", wrongRennId);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
        // then

    }

    @Test
    void testUpdateTire() {
        // given
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        Tire r1 = new Tire(new Race(LocalDate.of(2021,06,03), "eifel"),
                serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                new Time(18, 30, 0), new Time(20, 0, 0));
        tireRepository.save(r1);
        // when
        Tire r2 = new Tire(r1.tireID, r1.race, "serial", "bezeich", LocalDate.of(2011, 11, 11),
                new Time(11, 11, 11), "spezifi", "session", 1.1, 1.2, 1.3, 1.4, 1, 2, 3, new Time(12, 12, 12),
                new Time(13, 13, 13), 2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 3.1, 3.2, 3.3, 3.4, "abgeb");

        tireService.updateTire(r2.tireID, r2.bezeichnung, r2.datum, Optional.of(r2.tp_hot1),
                Optional.of(r2.tp_hot2), Optional.of(r2.tp_hot3), Optional.of(r2.tp_hot4), Optional.of(r2.bleed_hot1),
                Optional.of(r2.bleed_hot2), Optional.of(r2.bleed_hot3), Optional.of(r2.bleed_hot4),
                Optional.of(r2.bleed_in_blanket), r2.abgegeben_fuer, r2.heatingStart, r2.heatingStop,
                Optional.of(r2.heatingTemp), Optional.of(r2.heatingTime), Optional.of(r2.kaltdruck1),
                Optional.of(r2.kaltdruck2), Optional.of(r2.kaltdruck3), Optional.of(r2.kaltdruck4),
                Optional.of(r2.kaltdruckTemp), r2.serialNumber, r2.spez, Optional.of(r2.target), r2.uhrzeit,
                r2.race.getRaceID());
        // then
        String expected = "Tire{" +
                "reifenid=" + r2.tireID +
                ", rennid= " + r2.race.getRaceID() +
                ", serialNumber='" + r2.serialNumber + '\'' +
                ", bezeichnung='" + r2.bezeichnung + '\'' +
                ", datum=" + r2.datum +
                ", uhrzeit=" + r2.uhrzeit +
                ", spez='" + r2.spez + '\'' +
                ", session='" + r2.session + '\'' +
                ", kaltdruck1=" + r2.kaltdruck1 +
                ", kaltdruck2=" + r2.kaltdruck2 +
                ", kaltdruck3=" + r2.kaltdruck3 +
                ", kaltdruck4=" + r2.kaltdruck4 +
                ", kaltdruckTemp=" + r2.kaltdruckTemp +
                ", heatingTemp=" + r2.heatingTemp +
                ", heatingTime=" + r2.heatingTime +
                ", heatingStart=" + r2.heatingStart +
                ", heatingStop=" + r2.heatingStop +
                ", bleed_in_blanket=" + r2.bleed_in_blanket +
                ", tp_hot1=" + r2.tp_hot1 +
                ", tp_hot2=" + r2.tp_hot2 +
                ", tp_hot3=" + r2.tp_hot3 +
                ", tp_hot4=" + r2.tp_hot4 +
                ", target=" + r2.target +
                ", bleed_hot1=" + r2.bleed_hot1 +
                ", bleed_hot2=" + r2.bleed_hot2 +
                ", bleed_hot3=" + r2.bleed_hot3 +
                ", bleed_hot4=" + r2.bleed_hot4 +
                ", abgegeben_fuer='" + r2.abgegeben_fuer + '\'' +
                '}';
        assertThat(tireRepository.findById(13131313l)).toString().equals(r2.toString());
    }
}