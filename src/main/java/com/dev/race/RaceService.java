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

    public Race getRaceById(Long raceid) {
        return getRace(raceid);
    }

    public int[] getPrefixes(Long raceid) {
        Race race = getRace(raceid);
        System.out.println(race.getRaceID());
        return race.getPrefixes().getPrefs();

    }

    public List<Race> findAllByDate(LocalDate date) {
        List<Race> races = raceRepository.findRacesByDate(date);
        if (races.isEmpty()) {
            throw new IllegalStateException(String.format("No races with date %s were found.", date));
        }
        return races;
    }


    public double[] getPressureVars(Long raceid) {

        Race race = getRace(raceid);
        return race.getPressureVars();
    }

    public int getContingent(Long raceid) {
        Race race = getRace(raceid);
        if (race.getTireContingent() != 0) {
            return race.getTireContingent();

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
        Race race = getRace(raceid);

        if (prefInts.length != 6) {
            throw new IllegalStateException("Array must be exactly 6 integers long.");
        }

        race.setPrefixes(new TireMixturePrefixes(prefInts[0], prefInts[1], prefInts[2], prefInts[3], prefInts[4], prefInts[5]));

        return race.getPrefixes();
    }


    @Transactional
    public Race changePressureVariables(Long raceid, double[] pressureVars) {
        Race race = getRace(raceid);
        if (pressureVars.length != 4) {
            throw new IllegalStateException("Array must be exactly 4 doubles long.");
        }
        race.setPressureVars(pressureVars);
        return race;
    }

    @Transactional
    public void setContingent(int cont, Long raceid) {
        Race race = getRace(raceid);
        race.setTireContingent(cont);
    }

    @Transactional
    public void changeLength(Long raceid, double length) {
        Race race = getRace(raceid);
        if (race.getLength() == length) {
            return;
        }
        race.setLength(length);
    }

    // if available returns the race with given raceid, otherwise it returns the newest race. if nothing is found it
    // throws an error according to the problem.
    private Race getRace(Long raceid) {
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

    @Transactional
    public Race addNewNote(Long raceID, String note) {
        Race race = getRace(raceID);
        race.getNotes().add(note);
        return race;
    }

    @Transactional
    public Race changeNoteToDone(Long raceID, String note) {
        Race race = getRace(raceID);
        if (race.getNotes().contains(note)) {
            race.getNotes_done().add(note);
            race.getNotes().remove(note);
        } else {
            throw new IllegalStateException(String.format("Notes does not containt note with Content: %s.", note));
        }
        return race;
    }

    public void deleteNoteDone(Long raceID, String note) {
        Race race = getRace(raceID);
        if (race.getNotes_done().contains(note)) {
            race.getNotes_done().remove(note);
        } else {
            throw new IllegalStateException(
                    String.format("notes_done does not contain note with content: %s.", note));
        }
    }

    public void deleteNote(Long raceID, String note) {
        Race race = getRace(raceID);
        if (race.getNotes().contains(note)) {
            race.getNotes().remove(note);
        } else {
            throw new IllegalStateException(
                    String.format("notes does not contain note with content: %s.", note));
        }
    }

    public List<String> getAllNotes(Long raceID) {
        Race race = getRace(raceID);
        if (race.getNotes().isEmpty()) {
            throw new IllegalStateException("No notes available.");
        }
        return race.getNotes();
    }


    public List<String> getAllNotesDone(Long raceID) {
        Race race = getRace(raceID);
        if (race.getNotes_done().isEmpty()) {
            throw new IllegalStateException("No Notes_done are available.");
        }
        return race.getNotes_done();
    }
}
