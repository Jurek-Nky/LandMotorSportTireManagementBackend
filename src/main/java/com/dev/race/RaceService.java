package com.dev.race;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getRaces() {
        List<Race> races = (List<Race>) raceRepository.findAll();
        if (races.isEmpty()) {
            throw new IllegalStateException("No races availiable.");
        }
        return races;
    }

    public int[] getPrefixes(Long raceid) {
        Optional<Race> race;
        if (raceid != null) {
            race = raceRepository.findRaceByRaceID(raceid);
            if (race.isEmpty()) {
                throw new IllegalStateException(String.format("No race with ID %s was found.", raceid));
            }
        } else {
            race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
            if (race.isEmpty()) {
                throw new IllegalStateException("No race available.");
            }

        }
        System.out.println(race.get().getRaceID());
        return race.get().getPrefixes().getPrefs();

    }

    public List<Race> findAllByDate(LocalDate date) {
        List<Race> races = raceRepository.findRacesByDate(date);
        if (races.isEmpty()) {
            throw new IllegalStateException(String.format("No races with date %s were found.", date));
        }
        return races;
    }


    public double[] getPressureVars(Long raceid) {
        Optional<Race> race;
        if (raceid != null) {
            race = raceRepository.findRaceByRaceID(raceid);
            if (race.isEmpty()) {
                throw new IllegalStateException(String.format("No race with ID %s was found.", race));
            }
        } else {
            race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
            if (race.isEmpty()) {
                throw new IllegalStateException("No race available.");
            }
        }
        return race.get().getPressureVars();

    }

    public int getContingent() {
        Optional<Race> race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
        if (race.isEmpty()) {
            throw new IllegalStateException("No race available");
        }
        if (race.get().getTireContingent() != 0) {
            return race.get().getTireContingent();
        } else {
            throw new IllegalStateException("Contingent empty.");
        }
    }

    public Race addNewRace(Race race) {
        race.setPressureVars(new double[]{0, 0, 0, 0});
        race.setPrefixes(new TireMixturePrefixes(1, 2, 3, 4, 5, 6));
        return raceRepository.save(race);
    }

    @Transactional
    public TireMixturePrefixes changePrefixes(Long raceid, int[] prefInts) {
        Optional<Race> race;
        if (raceid != null) {
            race = raceRepository.findRaceByRaceID(raceid);
            if (race.isEmpty()) {
                throw new IllegalStateException(String.format("No race with ID %s was found", raceid));
            }
        } else {
            race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
            if (race.isEmpty()) {
                throw new IllegalStateException("No race available.");
            }
        }

        if (prefInts.length != 6) {
            throw new IllegalStateException("Array must be exactly 6 integers long.");
        }

        race.get().setPrefixes(new TireMixturePrefixes(prefInts[0], prefInts[1], prefInts[2], prefInts[3], prefInts[4], prefInts[5]));

        return race.get().getPrefixes();
    }

    @Transactional
    public Race changePressureVariables(Long raceid, double[] pressureVars) {
        Optional<Race> race;
        if (raceid != null) {
            race = raceRepository.findRaceByRaceID(raceid);
            if (race.isEmpty()) {
                throw new IllegalStateException(String.format("No race with ID %s was found", raceid));
            }
        } else {
            race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
            if (race.isEmpty()) {
                throw new IllegalStateException("No race available.");
            }
        }
        if (pressureVars.length != 4) {
            throw new IllegalStateException("Array must be exactly 4 doubles long.");
        }
        race.get().setPressureVars(pressureVars);
        return race.get();
    }


    public void deleteRaceById(Long raceid) {
        if (raceRepository.findRaceByRaceID(raceid).isEmpty()) {
            throw new IllegalStateException(String.format("No race with ID %s was found.", raceid));
        }
        raceRepository.deleteById(raceid);
    }

    @Transactional
    public void setContingent(int cont) {
        Optional<Race> race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
        if (race.isEmpty()) {
            throw new IllegalStateException("No race available");
        }
        race.get().setTireContingent(cont);
    }
}
