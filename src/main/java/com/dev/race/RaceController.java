package com.dev.race;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @ApiOperation(value = "returns all races stored in the system.")
    public List<Race> getAllRaces() {
        return raceService.getRaces();
    }

    @GetMapping(path = "/date")
    @ApiOperation(value = "find races by date.")
    public List<Race> getRacesByDate(@RequestAttribute(name = "date") LocalDate date) {
        return raceService.findAllByDate(date);
    }

    @GetMapping(path = "/prefixes")
    @ApiOperation(value = "return the integer prefixes for tiremixture in the following order:\nHot, ")
    public int[] getTireMixtureMap(@RequestParam(name = "id", required = false) Long raceid) {
        return raceService.getPrefixes(raceid);
    }

    @GetMapping(path = "/pressureVars")
    @ApiOperation(value = "returs the variables for pressure calculation")
    public double[] getPressureVars(@RequestParam(name = "id", required = false) Long raceid) {
        return raceService.getPressureVars(raceid);

    }


    @GetMapping(path = "/id")
    @ApiOperation(value = "returns the race by id")
    public Race getRaceById(@RequestParam(name = "id") Long raceid) {
        return raceService.getRaceById(raceid);
    }

    @GetMapping(path = "/contingent")
    public int getCont(@RequestParam(name = "id", required = false) Long raceid) {
        return raceService.getContingent(raceid);

    }

    @PostMapping("/new")
    @ApiOperation(value = "insert new race.")
    public Race addNewRace(@RequestBody() Race race) {
        return raceService.addNewRace(race);
    }

    @PutMapping("/prefixes")
    @ApiOperation(value = "changes the prefixes for a give race. Its important that the correct order is used(hot,medium,cold,intermediate,dry_wet,heavy_wet).")
    public TireMixturePrefixes changePrefixes(@RequestParam(name = "id", required = false) Long raceid,
                                              @RequestBody int[] ints) {
        return raceService.changePrefixes(raceid, ints);
    }

    @PutMapping("/contingent")

    @ApiOperation(value = "Change contingent for the selected/newest race")
    public void setTireContingent(@RequestParam(name = "cont") int cont,
                                  @RequestParam(name = "raceid") Long raceid) {
        raceService.setContingent(cont, raceid);
    }

    @PutMapping("/pressure")
    @ApiOperation(value = "change all variables for calculating the tire pressure.")
    public Race changePressureVars(@RequestParam(name = "id", required = false) Long raceid,
                                   @RequestBody double[] pressureVars) {
        return raceService.changePressureVariables(raceid, pressureVars);
    }

    @PutMapping("/length")
    @ApiOperation(value = "change the lentgh of a given race")
    public void changeLength(@RequestParam(name = "id", required = false) Long raceid,
                             @RequestParam(name = "len") double length) {
        raceService.changeLength(raceid, length);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRaceById(@PathVariable(name = "id") Long raceid) {
        raceService.deleteRaceById(raceid);
    }
}
