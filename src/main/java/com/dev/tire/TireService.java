package com.dev.tire;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import net.bytebuddy.jar.asm.commons.Remapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
// TireService is the layer between the API and the data management.
public class TireService {

    private final TireRepository tireRepository;
    private final RaceRepository raceRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public TireService(TireRepository tireRepository, RaceRepository raceRepository) {
        this.tireRepository = tireRepository;
        this.raceRepository = raceRepository;
    }

    /*##########################################################################################################
     *  methodes for GET requests
     */
    public List<com.dev.tire.Tire> getTires() {
        List<Tire> tires = (List<Tire>) tireRepository.findAll();
        if (tires.isEmpty()) {
            throw new IllegalStateException("No tires were found.");
        }
        return tires;
    }

    public List<Tire> findTiresByBezeichnung(String bezeichnung) {
        List<Tire> tires = tireRepository.findTiresByBezeichnung(bezeichnung);
        if (tires.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with Bezeichnung: %s", bezeichnung));
        }
        return tires;
    }

    public Optional<Tire> findTireById(Long tireID) {
        Optional<Tire> tire = tireRepository.findTireByTireID(tireID);
        if (tire.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with TireID: %s", tireID));
        }
        return tire;
    }

    public Optional<Tire> findTireBySerialnumber(String serialnumber) {
        Optional<Tire> tire = tireRepository.findTireBySerialNumber(serialnumber);
        if (tire.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with Serialnumber: %s", serialnumber));
        }
        return tire;
    }

    public List<Tire> findTiresByRennId(Long raceID) {
        List<Tire> tires = tireRepository.findTiresByRace_RaceID(raceID);
        if (tires.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with RennID: %s", raceID));
        }
        return tires;
    }

    /*##########################################################################################################
     *  methodes for POST requests
     */
    public Tire addNewTire(Tire tire) {
        tireRepository.save(tire);
        return tire;
    }

    public Tire addNewTire(TireDto tireDto) {
        // adding tire using DataTransferObject to make it easier to work with foreign fields.
        Optional<Race> race = raceRepository.findRaceByRaceID(tireDto.raceID);
        if (race.isEmpty()) {
            throw new IllegalStateException(String.format("No race with raceID %s found.", tireDto.raceID));
        }
        Optional<Tire> tireSerialTest = tireRepository.findTireBySerialNumber(tireDto.serialNumber);
        if (tireSerialTest.isPresent()) {
            throw new IllegalStateException(String.format("Tire with serialnumber %s already exists.", tireDto.serialNumber));
        }
        Tire tire = new Tire(race.get(), tireDto.serialNumber, tireDto.bezeichnung,
                tireDto.date, tireDto.time, tireDto.spez, tireDto.session,
                tireDto.kaltdruck1, tireDto.kaltdruck2, tireDto.kaltdruck3,
                tireDto.kaltdruck4, tireDto.kaltdruckTemp, tireDto.heatingTemp,
                tireDto.heatingTime, tireDto.heatingStart, tireDto.heatingStop);
        tireDto.bleed_in_blanket.ifPresent(tire::setBleed_in_blanket);
        tireDto.tp_hot1.ifPresent(tire::setTp_hot1);
        tireDto.tp_hot2.ifPresent(tire::setTp_hot2);
        tireDto.tp_hot3.ifPresent(tire::setTp_hot3);
        tireDto.tp_hot4.ifPresent(tire::setTp_hot4);
        tireDto.target.ifPresent(tire::setTarget);
        tireDto.bleed_hot1.ifPresent(tire::setBleed_hot1);
        tireDto.bleed_hot2.ifPresent(tire::setBleed_hot2);
        tireDto.bleed_hot3.ifPresent(tire::setBleed_hot3);
        tireDto.bleed_hot4.ifPresent(tire::setBleed_hot4);
        if (tireDto.abgegeben_fuer != null && !tireDto.abgegeben_fuer.isEmpty()) {
            tire.setAbgegeben_fuer(tireDto.abgegeben_fuer);
        }
        tire.setRace(race.get());
        tireRepository.save(tire);
        return tire;
    }

    public Tire addNewTire(Long raceid, String serialnumber, String bezeichnung,
                           String date, String time, String spez, String session,
                           Double kaltdruck1, Double kaltdruck2,
                           Double kaltdruck3, Double kaltdruck4, int kaltdruckTemp,
                           int heatingTemp, int heatingTime, String heatingStart,
                           String heatingStop, Optional<Double> tp_hot1, Optional<Double> tp_hot2,
                           Optional<Double> tp_hot3, Optional<Double> tp_hot4,
                           Optional<Double> bleed_hot1, Optional<Double> bleed_hot2,
                           Optional<Double> bleed_hot3, Optional<Double> bleed_hot4,
                           String abgegeben_fuer, Optional<Double> bleed_in_blanket,
                           Optional<Double> target) {
        if (tireRepository.findTireBySerialNumber(serialnumber).isPresent()) {
            throw new IllegalStateException(String.format("Tire with serialnumber %s already exists", serialnumber));
        }
        Optional<Race> race = raceRepository.findRaceByRaceID(raceid);
        if (race.isEmpty()) {
            throw new IllegalStateException(String.format("Race with id %s could not be found.", raceid));
        }
        System.out.println(raceid + " :  " + serialnumber + " :  " +
                bezeichnung + " :  " + date + " :  " + time + " :  " + spez + " :  " +
                session + " :  " + kaltdruck1 + " :  " + kaltdruckTemp + " :  " +
                heatingTemp + " :  " + heatingTime + " :  " + heatingStart + " :  " + heatingStop);
        LocalDate d = LocalDate.parse(date);
        Time t = Time.valueOf(date.replace('-', ':'));
        Time htstart = Time.valueOf(heatingStart.replace('-', ':'));
        Time htstop = Time.valueOf(heatingStop.replace('-', ':'));
        Tire tire = new Tire(race.get(), serialnumber, bezeichnung, d, t, spez,
                session, kaltdruck1, kaltdruck2, kaltdruck3, kaltdruck4,
                kaltdruckTemp, heatingTemp, heatingTime, htstart, htstop);

        tp_hot1.ifPresent(tire::setTp_hot1);
        tp_hot2.ifPresent(tire::setTp_hot2);
        tp_hot3.ifPresent(tire::setTp_hot3);
        tp_hot4.ifPresent(tire::setTp_hot4);
        bleed_hot1.ifPresent(tire::setBleed_hot1);
        bleed_hot2.ifPresent(tire::setBleed_hot2);
        bleed_hot3.ifPresent(tire::setBleed_hot3);
        bleed_hot4.ifPresent(tire::setBleed_hot4);
        bleed_in_blanket.ifPresent(tire::setBleed_in_blanket);
        target.ifPresent(tire::setTarget);
        if (abgegeben_fuer != null && !abgegeben_fuer.isEmpty()) {
            tire.setAbgegeben_fuer(abgegeben_fuer);
        }
        tireRepository.save(tire);
        return tire;
    }

    /*##########################################################################################################
     *  methodes for DELETE requests
     */
    public void deleteTire(Long tireID) {
        if (!tireRepository.existsById(tireID)) {
            throw new IllegalStateException(String.format("tire with id %s does not exist.", tireID));
        } else {
            tireRepository.deleteById(tireID);
        }
    }

    /*##########################################################################################################
     *  methodes for PUT requests
     */

    @Transactional
    // This methode checks every given argument for existence and equality to the tire field and replaces the tire field if necessary
    public void updateTire(Long tireID, String bezeichnung, LocalDate date, Optional<Double> tp_hot1, Optional<Double> tp_hot2, Optional<Double> tp_hot3,
                           Optional<Double> tp_hot4, Optional<Double> bleed_hot1, Optional<Double> bleed_hot2, Optional<Double> bleed_hot3, Optional<Double> bleed_hot4,
                           Optional<Double> bleed_in_blanket, String abgegeben_fuer, Time heatingStart, Time heatingStop, Optional<Integer> heatingTemp,
                           Optional<Integer> heatingTime, Optional<Double> kaltdruck1, Optional<Double> kaltdruck2, Optional<Double> kaltdruck3, Optional<Double> kaltdruck4,
                           Optional<Integer> kaltdruckTemp, String serialnumber, String spez, Optional<Double> target, Time time, Long rennid) {
        Tire tire = tireRepository.findTireByTireID(tireID).orElseThrow(() ->
                new IllegalStateException(String.format("Tire with id %s could not be found.", tireID)));
        if (bezeichnung != null && bezeichnung.length() > 0 && !tire.bezeichnung.equals(bezeichnung)) {
            tire.setBezeichnung(bezeichnung);
        }
        if (abgegeben_fuer != null && abgegeben_fuer.length() > 0 && !Objects.equals(tire.abgegeben_fuer, abgegeben_fuer)) {
            tire.setAbgegeben_fuer(abgegeben_fuer);
        }
        if (serialnumber != null && serialnumber.length() > 0 && !tire.serialNumber.equals(serialnumber)) {
            tire.setSerialNumber(serialnumber);
        }
        if (spez != null && spez.length() > 0 && !tire.spez.equals(spez)) {
            tire.setSpez(spez);
        }
        if (date != null && !tire.date.equals(date)) {
            tire.setDate(date);
        }
        if (tp_hot1.isPresent() && tire.tp_hot1 != tp_hot1.get()) {
            tire.setTp_hot1(tp_hot1.get());
        }
        if (tp_hot2.isPresent() && tire.tp_hot2 != tp_hot2.get()) {
            tire.setTp_hot2(tp_hot2.get());
        }
        if (tp_hot3.isPresent() && tire.tp_hot3 != tp_hot3.get()) {
            tire.setTp_hot3(tp_hot3.get());
        }
        if (tp_hot4.isPresent() && tire.tp_hot4 != tp_hot4.get()) {
            tire.setTp_hot4(tp_hot4.get());
        }
        if (bleed_hot1.isPresent() && tire.bleed_hot1 != bleed_hot1.get()) {
            tire.setBleed_hot1(bleed_hot1.get());
        }
        if (bleed_hot2.isPresent() && tire.bleed_hot2 != bleed_hot2.get()) {
            tire.setBleed_hot2(bleed_hot2.get());
        }
        if (bleed_hot3.isPresent() && tire.bleed_hot3 != bleed_hot3.get()) {
            tire.setBleed_hot3(bleed_hot3.get());
        }
        if (bleed_hot4.isPresent() && tire.bleed_hot4 != bleed_hot4.get()) {
            tire.setBleed_hot4(bleed_hot4.get());
        }
        if (bleed_in_blanket.isPresent() && tire.bleed_in_blanket != bleed_in_blanket.get()) {
            tire.setBleed_in_blanket(bleed_in_blanket.get());
        }
        if (heatingStart != null && tire.heatingStart != heatingStart) {
            tire.setHeatingStart(heatingStart);
        }
        if (heatingStop != null && tire.heatingStop != heatingStop) {
            tire.setHeatingStop(heatingStop);
        }
        if (heatingTemp.isPresent() && tire.heatingTemp != heatingTemp.get()) {
            tire.setHeatingTemp(heatingTemp.get());
        }
        if (heatingTime.isPresent() && tire.heatingTime != heatingTime.get()) {
            tire.setHeatingTime(heatingTime.get());
        }
        if (kaltdruck1.isPresent() && tire.kaltdruck1 != kaltdruck1.get()) {
            tire.setKaltdruck1(kaltdruck1.get());
        }
        if (kaltdruck2.isPresent() && tire.kaltdruck2 != kaltdruck2.get()) {
            tire.setKaltdruck2(kaltdruck2.get());
        }
        if (kaltdruck3.isPresent() && tire.kaltdruck3 != kaltdruck3.get()) {
            tire.setKaltdruck3(kaltdruck3.get());
        }
        if (kaltdruck4.isPresent() && tire.kaltdruck4 != kaltdruck4.get()) {
            tire.setKaltdruck4(kaltdruck4.get());
        }
        if (kaltdruckTemp.isPresent() && tire.kaltdruckTemp != kaltdruckTemp.get()) {
            tire.setKaltdruckTemp(kaltdruckTemp.get());
        }
        if (target.isPresent() && tire.target != target.get()) {
            tire.setTarget(target.get());
        }
        if (time != null && !tire.time.equals(time)) {
            tire.setTime(time);
        }
        if (rennid != null && !tire.race.getRaceID().equals(rennid)) {
            tire.setRace(raceRepository.findRaceByRaceID(rennid).orElseThrow(() -> new IllegalStateException(String.format("Rennen with id %s not found.", rennid))));
        }

    }


}

