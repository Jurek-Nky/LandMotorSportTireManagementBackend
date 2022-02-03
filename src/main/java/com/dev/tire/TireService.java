package com.dev.tire;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
// TireService is the layer between the API and the data management.
public class TireService {

    private final TireRepository tireRepository;
    private final RaceRepository raceRepository;
    private final TireSetRepository tireSetRepository;


    @Autowired
    public TireService(TireRepository tireRepository, RaceRepository raceRepository, TireSetRepository tireSetRepository) {
        this.tireRepository = tireRepository;
        this.raceRepository = raceRepository;
        this.tireSetRepository = tireSetRepository;
    }

    public Optional<Tire> findTireById(Long tireID) {
        Optional<Tire> tire = tireRepository.findTireByTireID(tireID);
        if (tire.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with TireID: %s", tireID));
        }
        return tire;
    }

    @Transactional
    // This methode checks every given argument for existence and equality to the tire field and replaces the tire field if necessary
    public Tire updateTire(Long tireID,
                           String serial,
                           String bez,
                           String mischung,
                           String art,
                           Time erhalten_um,
                           String session,
                           Optional<Integer> laufleistung,
                           Optional<Double> kaltdruck,
                           Optional<Integer> kaltdruckTemp,
                           Optional<Integer> kaltdruckTempMeasured,
                           Optional<Double> kaltdruckModified,
                           boolean bleeded,
                           Optional<Integer> heatingTemp,
                           Optional<Integer> heatingTime,
                           Time heatingStart,
                           Time heatingStop) {
        Tire tire = tireRepository.findTireByTireID(tireID).orElseThrow(() ->
                new IllegalStateException(String.format("Tire with id %s could not be found.", tireID)));
        if (serial != null && serial.length() > 0 && !tire.serialNumber.equals(serial)) {
            tire.setSerialNumber(serial);
        }
        if (bez != null && bez.length() > 0 && !tire.bezeichnung.equals(bez)) {
            tire.setBezeichnung(bez);
        }
        if (mischung != null && mischung.length() > 0 && !tire.mischung.equals(mischung)) {
            tire.setMischung(mischung);
        }
        if (art != null && art.length() > 0 && !tire.art.equals(art)) {
            tire.setArt(art);
        }
        if (erhalten_um != null && tire.erhaltenUm != erhalten_um) {
            tire.setErhaltenUm(erhalten_um);
        }
        if (session != null && session.length() > 0 && !tire.session.equals(session)) {
            tire.setSession(session);
        }
        laufleistung.ifPresent(tire::setLaufleistung);
        kaltdruck.ifPresent(tire::setKaltdruck);
        kaltdruckTemp.ifPresent(tire::setKaltdruckTemp);
        kaltdruckTempMeasured.ifPresent(tire::setKaltdruckTempMeasured);
        kaltdruckModified.ifPresent(tire::setKaltdruckModified);
        tire.setBleeded(bleeded);
        heatingTemp.ifPresent(tire::setHeatingTemp);
        heatingTime.ifPresent(tire::setHeatingTime);
        if (heatingStart != null && tire.heatingStart != heatingStart) {
            tire.setHeatingStart(heatingStart);
        }
        if (heatingStop != null && tire.heatingStop != heatingStop) {
            tire.setHeatingStop(heatingStop);
        }

        return tire;
    }

    @Transactional
    public Timestamp setOrderTimer(int minutes) {
        Optional<Race> race = raceRepository.findBySelected(true);
        if (race.isEmpty()) {
            throw new IllegalStateException("No race was found.");
        }
        Timestamp ts = new Timestamp(System.currentTimeMillis() + minutes * 60000L);
        race.get().setOrderReady(ts);
        return race.get().getOrderReady();
    }

    public String getOrderTimer() {
        Optional<Race> race = raceRepository.findBySelected(true);
        if (race.isEmpty()) {
            throw new IllegalStateException("No race was found.");
        }
        return race.get().getOrderReady().toString();
    }

    @Transactional
    public void updateModification(Long tireID, String mod) {
        Optional<Tire> tire = tireRepository.findTireByTireID(tireID);
        if (tire.isEmpty()) {
            throw new IllegalStateException(String.format("Tire with ID %s does not exist.", tireID));
        }
        tire.get().setModification(mod);
    }

    public List<Tire> getAllTires() {
        List<Tire> tires = (List<Tire>) tireRepository.findAll();
        if (tires.isEmpty()) {
            throw new IllegalStateException("no tires found.");
        }
        return tires;
    }
}

