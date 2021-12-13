package com.dev.race;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/manage/race")
public class RaceManagementController {
    private final RaceService raceService;

    @Autowired
    public RaceManagementController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping
    @ApiOperation(value = "insert new race.")
    public Race addNewRace(@RequestBody(required = true) Race race) {
        return raceService.addNewRace(race);
    }

    @PutMapping("/prefixes")
    @ApiOperation(value = "changes the prefixes for a give race. Its important that the correct order is used(hot,medium,cold,intermediate,dry_wet,heavy_wet).")
    public TireMixturePrefixes changePrefixes(@RequestParam(required = false) Long raceid,
                                              @RequestBody int[] ints) {
        return raceService.changePrefixes(raceid, ints);
    }

    @PutMapping("/contingent")
    public void setTireContingent(@RequestParam(name = "cont") int cont) {
        raceService.setContingent(cont);
    }

    @PutMapping("/pressure")
    @ApiOperation(value = "change all variables for calculating the tire pressure.")
    public Race changePressureVars(@RequestParam(required = false) Long raceid, @RequestBody double[] pressureVars) {
        return raceService.changePressureVariables(raceid, pressureVars);
    }

    @DeleteMapping("/{id}")
    public void deleteRaceById(@PathVariable(name = "id") Long raceid) {
        raceService.deleteRaceById(raceid);
    }
}
