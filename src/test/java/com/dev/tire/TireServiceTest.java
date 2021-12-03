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
        race = raceRepository.save(new Race(LocalDate.of(2012, 12, 12), "cooles race", "schwarzwald"));
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
    void getTiresWithElements() throws JsonProcessingException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());
        Tire tire1 = tireRepository.save(new TestTire(race).getTire());

        List<Tire> tires = tireService.getTires();

        assertThat(tires.size()).isEqualTo(2);
        assertThat(om.writeValueAsString(tires)).isEqualTo(om.writeValueAsString(List.of(tire, tire1)));
    }

    @Test
    void testFindTiresByBezeichnungWithElement() throws JsonProcessingException {
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
    void testFindTireByIdWithElement() throws JsonProcessingException {
        Tire tire = tireRepository.save(new TestTire(race).getTire());

        Optional<Tire> tireFromService = tireService.findTireById(tire.tireID);

        assertThat(tireFromService.isPresent()).isTrue();
        assertThat(om.writeValueAsString(tireFromService.get())).isEqualTo(om.writeValueAsString(tire));
    }

    @Test
    void testFindTireByIdWithoutElement() {
        Long id = 316426946L;
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTireById(id));
        String expected_message = String.format("No tires were found with TireID: %s", id);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testFindTireBySerialnumberWithElement() throws JsonProcessingException {
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
    void testFindTiresByRennIdWithElement() throws JsonProcessingException {
        race = raceRepository.save(race);
        Tire tire = tireRepository.save(new TestTire(race).getTire());
        Tire tire1 = tireRepository.save(new TestTire(race).getTire());

        List<Tire> tires = tireService.findTiresByRennId(race.getRaceID());

        assertThat(tires.size()).isEqualTo(2);
        assertThat(om.writeValueAsString(tires)).isEqualTo(om.writeValueAsString(List.of(tire, tire1)));
    }

    @Test
    void testFindTiresByRennIdWithoutElement() {
        Long rennid = 631452L;
        Exception exception = assertThrows(RuntimeException.class, () -> tireService.findTiresByRennId(rennid));
        String expected_message = String.format("No tires were found with RennID: %s", rennid);
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void addNewTire() {
        Tire tire = tireService.addNewTire(new TestTire(race).getTire());

        assertThat(tireRepository.findTireByTireID(tire.tireID)).isPresent();
    }

    @Test
    void addNewTireNoRace() {
        TireDto tireDto = new TestTire(race).getTireDto();
        raceRepository.deleteAll();
        Exception exception = assertThrows(RuntimeException.class,
                () -> tireService.addNewTire(race.getRaceID(),tireDto));
        String expectedException = String.format("No race with ID %s was found.", race.getRaceID());
        assertThat(exception.getMessage()).isEqualTo(expectedException);
    }

    @Test
    @Disabled
    void addNewTireSerialAlreadyExists() {
        TestTire testTire = new TestTire(race);

        Tire tire = tireRepository.save(testTire.getTire());

        Exception e = assertThrows(RuntimeException.class,
                () -> tireService.addNewTire(race.getRaceID(),testTire.getTireDto()));
        String expected = String.format("Tire with serialnumber %s already exists.", tire.getSerialNumber());
        assertThat(e.getMessage()).isEqualTo(expected);
    }

    @Test
    void addNewTireWithDto() {

        TireDto tireDto = new TestTire(race).getTireDto();

        Tire tire = tireService.addNewTire(race.getRaceID(),tireDto);

        assertThat(tire).isInstanceOf(Tire.class);
    }

    @Test
    void testDeleteTireWithElement() {
        Tire tire = tireRepository.save(new TestTire(race).getTire());

        tireService.deleteTire(tire.tireID);
        List<Tire> tires = (List<Tire>) tireRepository.findAll();

        assertThat(tires.isEmpty()).isTrue();
    }

    @Test
    void testDeleteTireWithoutElement() {
        Long id = 63649L;
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
        String expected_message = String.format("Tire with id %s could not be found.", (Object) null);

        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testUpdateTireForExceptionNoRennenFound() {
        Tire tire = tireRepository.save(new TestTire(race).getTire());
        race = raceRepository.save(new Race(LocalDate.of(2012, 12, 12), "cooles race", "schwarzwald"));
        raceRepository.deleteById(race.getRaceID());

        Exception exception = assertThrows(RuntimeException.class,
                () -> tireService.updateTire(tire.tireID, race.getRaceID(), null, null,
                        null, null, null, null,
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), null, null));

        String expected_message = String.format("No race with id %s was found.", race.getRaceID());
        assertThat(exception.getMessage()).isEqualTo(expected_message);
    }

    @Test
    void testUpdateTire() throws JsonProcessingException {
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