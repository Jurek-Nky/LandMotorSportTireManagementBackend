package com.dev.tire;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.annotation.XmlAttachmentRef;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TireSetService {
    private final TireSetRepository tireSetRepository;
    private final RaceRepository raceRepository;
    private final TireRepository tireRepository;

    @Autowired
    public TireSetService(TireSetRepository tireSetRepository, RaceRepository raceRepository, TireRepository tireRepository) {
        this.tireSetRepository = tireSetRepository;
        this.raceRepository = raceRepository;
        this.tireRepository = tireRepository;
    }

    public List<TireSet> getTireSetByStatus(String status) {
        List<TireSet> tireSets = tireSetRepository.findByStatus(status);
        if (tireSets.isEmpty()) {
            throw new IllegalStateException(String.format("No tiresets with status %s were found.", status));
        }
        return tireSets;
    }

    @Transactional
    public TireSet changeStatus(Long tireSetId, String status) {
        Optional<TireSet> tireSet = tireSetRepository.findById(tireSetId);
        if (tireSet.isEmpty()) {
            throw new IllegalStateException(String.format("No Tireset with ID %s was found.", tireSetId));
        }
        switch (status) {
            case "auf lager" -> {
                tireSet.get().setStatus(status);
                for (Tire tire : tireSet.get().tires) {
                    tire.setErhaltenUm(Time.valueOf(LocalTime.now()));
                }
            }
            case "benutzt" -> tireSet.get().setStatus(status);
            default -> throw new IllegalStateException(String.format("%s must be one of [auf lager, benutzt]", status));
        }
        return tireSet.get();
    }

    public void deleteTireSet(Long tireSetId) {
        if (!tireSetRepository.existsById(tireSetId)) {
            throw new IllegalStateException(String.format("Tireset with ID %s was not found.", tireSetId));
        }
        tireSetRepository.deleteById(tireSetId);
    }

    public List<TireSet> getAllTireSets() {
        List<TireSet> tireSets = (List<TireSet>) tireSetRepository.findAll();
        if (tireSets.isEmpty()) {
            throw new IllegalStateException("No tireSets were found.");
        }
        return tireSets;
    }

    public TireSet addNewTireSet(Long raceid, TireSet tireSet) {
        Optional<Race> race;
        if (raceid != null) {
            race = raceRepository.findRaceByRaceID(raceid);
        } else {
            race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
        }
        if (race.isEmpty()) {
            throw new IllegalStateException("No Race available");
        }
        Optional<TireSet> lastSet = tireSetRepository.findFirstByRace_RaceIDEqualsOrderByTireSetNrDesc(race.get().getRaceID());
        int nr;
        if (lastSet.isEmpty()) {
            nr = 1;
        } else {
            nr = lastSet.get().getTireSetNr() + 1;
        }
        List<Tire> tires = new ArrayList<>();
        for (Tire tire : tireSet.tires) {
            Tire t = new Tire(tire.mischung, tire.art);
            t.setPosition(tire.position);
            if (tire.getModification() != null) {
                t.setModification(tire.modification);
            }
            t.setBestelltUm(Time.valueOf(LocalTime.now()));
            t.setTireSet(tireSet);
            if (t.getMischung().equals("Heavy_wet") || tire.getMischung().equals("Dry_wet")) {
                t.setHeatingTemp(40);
            } else {
                t.setHeatingTemp(90);
            }
            t.setBezeichnung(String.format("%s%02d", race.get().getPrefixes().getprefix(t.getMischung()), nr));
            tires.add(t);
        }
        tireSet.setTires(tires);
        tireSet.setTireSetNr(nr);
        tireSet.setRace(race.get());
        tireSet.setStatus("bestellt");
        race.get().decreaseTireContingent();
        return tireSetRepository.save(tireSet);
    }

    @Transactional
    public TireSet startHeating(Long tireSetID) {
        Optional<TireSet> tireSet = tireSetRepository.findByTires_TireSet_ID(tireSetID);
        if (tireSet.isEmpty()) {
            throw new IllegalStateException(String.format("No Tireset with ID %s was found.", tireSetID));
        }
        for (Tire tire : tireSet.get().tires) {
            tire.setHeatingStart(Time.valueOf(LocalTime.now()));
        }
        return tireSet.get();
    }

    @Transactional
    public TireSet stopHeating(Long tireSetID) {
        Optional<TireSet> tireSet = tireSetRepository.findByTires_TireSet_ID(tireSetID);
        if (tireSet.isEmpty()) {
            throw new IllegalStateException(String.format("No Tireset with ID %s was found.", tireSetID));
        }
        for (Tire tire : tireSet.get().tires) {
            tire.setHeatingStop(Time.valueOf(LocalTime.now()));
        }
        return tireSet.get();
    }

    @Transactional
    public void updateOrderTimer(Long tireSetID, Time orderTimer) {
        Optional<TireSet> tireSet = tireSetRepository.findByTires_TireSet_ID(tireSetID);
        if (tireSet.isEmpty()) {
            throw new IllegalStateException(String.format("TireSet with ID %s does not exist.", tireSetID));
        }
        tireSet.get().setOrderTimer(orderTimer);
    }

    public Time getOrderTimerByID(Long tireSetID) {
        Optional<TireSet> tireSet = tireSetRepository.findByTires_TireSet_ID(tireSetID);
        if (tireSet.isEmpty()) {
            throw new IllegalStateException(String.format("TireSet with ID %s does not exist.", tireSetID));
        }
        if (tireSet.get().getOrderTimer() != null) {
            return tireSet.get().getOrderTimer();
        }
        throw new IllegalStateException("OrderTimer isn't available.");
    }
}
