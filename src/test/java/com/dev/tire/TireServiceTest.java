package com.dev.tire;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TireServiceTest {

    @Autowired
    private TireRepository tireRepository;
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private TireService tireService = new TireService(tireRepository, raceRepository);
    private Race race;
    ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    void beforeEach() {
        race = raceRepository.save(new Race(LocalDate.of(2012, 12, 12), "schwarzwald"));
    }

    @AfterEach
    void afterEach() {
        tireRepository.deleteAll();
        raceRepository.deleteAll();
    }

    @Test
    void getTiresWithoutElements() {
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.getTires());
        String expected_message = "No tires were found.";
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }


    @Test
    void getTiresWithElements() throws JsonProcessingException, ParseException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());
        Tire tire1 = tireRepository.save(new TestTire(race).getTire());

        List<Tire> tires = tireService.getTires();

        assertThat(tires.size()).isEqualTo(2);
        assertThat(om.writeValueAsString(tires)).isEqualTo(om.writeValueAsString(List.of(tire, tire1)));
    }

    @Test
    void testFindTiresByBezeichnungWithElement() throws JsonProcessingException, ParseException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());
        tireRepository.save(new TestTire(race).getTire());

        List<Tire> tires = tireService.findTiresByBezeichnung(tire.getBezeichnung());

        assertThat(tires.size()).isEqualTo(1);
        assertThat(om.writeValueAsString(tires)).isEqualTo(om.writeValueAsString(List.of(tire)));
    }

    @Test
    void testFindTiresByBezeichnungWithoutElement() {
        String bez = "lalala";
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTiresByBezeichnung(bez));
        String expected_message = String.format("No tires were found with Bezeichnung: %s", bez);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testFindTireByIdWithElement() throws JsonProcessingException, ParseException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());

        Optional<Tire> tireFromService = tireService.findTireById(tire.tireID);

        assertThat(tireFromService.isPresent()).isTrue();
        assertThat(om.writeValueAsString(tireFromService.get())).isEqualTo(om.writeValueAsString(tire));
    }

    @Test
    void testFindTireByIdWithoutElement() {
        Long id = 316426946l;
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTireById(id));
        String expected_message = String.format("No tires were found with TireID: %s", id);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testFindTireBySerialnumberWithElement() throws JsonProcessingException, ParseException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());

        Optional<Tire> tireFromService = tireService.findTireBySerialnumber(tire.serialNumber);

        assertThat(tireFromService.isPresent()).isTrue();
        assertThat(om.writeValueAsString(tireFromService.get())).isEqualTo(om.writeValueAsString(tire));
    }

    @Test
    void testFindTireBySerialnumberWithoutElement() {
        String serial = "lalala";
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTireBySerialnumber(serial));
        String expected_message = String.format("No tires were found with Serialnumber: %s", serial);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testFindTiresByRennIdWithElement() throws JsonProcessingException, ParseException {
        race = raceRepository.save(race);
        Tire tire = tireRepository.save(new TestTire(race).getTire());
        Tire tire1 = tireRepository.save(new TestTire(race).getTire());

        List<Tire> tires = tireService.findTiresByRennId(race.getRaceID());

        assertThat(tires.size()).isEqualTo(2);
        assertThat(om.writeValueAsString(tires)).isEqualTo(om.writeValueAsString(List.of(tire, tire1)));
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
        Tire tire = tireService.addNewTire(new TestTire(race).getTire());

        assertThat(tireRepository.findTireByTireID(tire.tireID)).isPresent();
    }

    @Test
    void addNewTireNoRace() throws ParseException {
        TireDto tireDto = new TestTire(race).getTireDto();
        raceRepository.deleteAll();
        Exception exception = assertThrows(RuntimeException.class,
                () -> tireService.addNewTire(tireDto));
        String expectedException = String.format("No race with raceID %s found.", tireDto.raceid);
        assertThat(exception.getMessage()).isEqualTo(expectedException);
    }

    @Test
    @Disabled
    void addNewTireSerialAlreadyExists() throws ParseException {
        TestTire testTire = new TestTire(race);

        Tire tire = tireRepository.save(testTire.getTire());

        Exception e = assertThrows(RuntimeException.class,
                () -> tireService.addNewTire(testTire.getTireDto()));
        String expected = String.format("Tire with serialnumber %s already exists.", tire.getSerialNumber());
        assertThat(e.getMessage()).isEqualTo(expected);
    }

    @Test
    void addNewTireWithDto() throws ParseException {
//        tireRepository.deleteAll();
//        race = new Race(LocalDate.of(12,12,12),"foobar");
        Race testRace = new Race(race.getRaceID(), LocalDate.of(12, 12, 12), "foobar");
        TireDto tireDto = new TestTire(testRace).getTireDto();

        Tire tire = tireService.addNewTire(tireDto);

        assertThat(tire).isInstanceOf(Tire.class);
    }

    @Test
    void testDeleteTireWithElement() throws ParseException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());

        tireService.deleteTire(tire.tireID);
        List<Tire> tires = (List<Tire>) tireRepository.findAll();

        assertThat(tires.isEmpty()).isTrue();
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
        Exception exception = assertThrows(RuntimeException.class,
                () -> tireService.updateTire(null, null, null, null, null,
                        null, null, null, Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), null, null));
        String expected_message = String.format("Tire with id %s could not be found.", null);

        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testUpdateTireForExceptionNoRennenFound() throws ParseException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());
        race = raceRepository.save(new Race(LocalDate.of(1, 1, 1), "lslsl"));
        raceRepository.deleteById(race.getRaceID());

        Exception exception = assertThrows(RuntimeException.class,
                () -> tireService.updateTire(tire.tireID, race.getRaceID(), null, null,
                        null, null, null, null,
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), null, null));

        String expected_message = String.format("No race with id %s was found.", race.getRaceID());
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testUpdateTire() throws JsonProcessingException, ParseException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());
        tireRepository.deleteById(tire.tireID);
        Tire tire2 = tireRepository.save(new TestTire(race).getTire());
        tire.setTireID(tire2.tireID);
        tireService.updateTire(tire2.tireID, tire.race.getRaceID(), tire.serialNumber,
                tire.bezeichnung, tire.mischung, tire.art, tire.erhaltenUm, tire.session,
                Optional.of(tire.kaltdruck), Optional.of(tire.kaltdruckTemp), Optional.of(tire.heatingTemp),
                Optional.of(tire.heatingTime), tire.heatingStart, tire.heatingStop);

        assertThat(om.writeValueAsString(tireRepository.findTireByTireID(tire2.tireID).get())).isEqualTo(om.writeValueAsString(tire));
    }
}