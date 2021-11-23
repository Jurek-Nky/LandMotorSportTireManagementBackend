package com.dev.race;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/race")
public class RaceController {

    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping(path = "/all")
    public List<Race> getAllRaces() {
        return raceService.getRaces();
    }

    @GetMapping(path = "/date")
    public List<Race> getRacesByDate(@RequestAttribute(name = "date") String date) {
        return raceService.findAllByDate(date);
    }

    @PostMapping("/new")
    public Race addNewRace(@RequestBody(required = true) Race race) {
        return raceService.addNewRace(race);
    }
}
