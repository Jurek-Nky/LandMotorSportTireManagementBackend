package com.dev.race;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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

    public List<Race> findAllByDate(LocalDate date) {
        List<Race> races = raceRepository.findRacesByDate(Date.valueOf(date));
        if (races.isEmpty()) {
            throw new IllegalStateException(String.format("No races with date %s were found.", date));
        }
        return races;
    }

    public Race addNewRace(String date, String location) {
        LocalDate d = LocalDate.parse(date);
        Race race = new Race(d, location);
        raceRepository.save(race);
        return race;
    }

    public Race addNewRace(Race race) {
        raceRepository.save(race);
        return race;
    }
}
