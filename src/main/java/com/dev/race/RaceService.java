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
    public RaceService(RaceRepository raceRepository){
        this.raceRepository = raceRepository;
    }

    public List<Race> getRaces() {
        return (List<Race>) raceRepository.findAll();
    }

    public List<Race> findAllByDate(String date) {
        LocalDate d = LocalDate.parse(date);
        return raceRepository.findRacesByDate(Date.valueOf(d));
    }
}
