package com.dev.tire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tireset")
public class TireSetController {
    private final TireSetService tireSetService;

    @Autowired
    public TireSetController(TireSetService tireSetService) {
        this.tireSetService = tireSetService;
    }

    @PostMapping("/new")
    public TireSet addNewTireSet(@RequestParam(required = false) Long raceid, @RequestBody() TireSet tireSet) {
        return tireSetService.addNewTireSet(raceid, tireSet);
    }

    @GetMapping("/status")
    public List<TireSet> getTireSetByStatus(@RequestParam(name = "status") String status) {
        return tireSetService.getTireSetByStatus(status);
    }

    @GetMapping("/all")
    public List<TireSet> getAllTireSets() {
        return tireSetService.getAllTireSets();
    }

    @PutMapping("/update/{tireSetID}/status")
    public TireSet updateTireSetStatus(@PathVariable("tireSetID") Long tireSetId,
                                       @RequestParam(name = "status") String status) {
        return tireSetService.changeStatus(tireSetId, status);
    }

    @PutMapping("/update/{tireSetID}/heatingStart")
    public TireSet startHeating(@PathVariable("tireSetID") Long tireSetID) {
        return tireSetService.startHeating(tireSetID);
    }

    @PutMapping("/update/{tireSetID}/heatingStop")
    public TireSet stopHeating(@PathVariable("tireSetID") Long tireSetID) {
        return tireSetService.stopHeating(tireSetID);
    }

    @DeleteMapping("/delete/{tireSetID}")
    public void deleteTireSet(@PathVariable("tireSetID") Long tireSetId) {
        tireSetService.deleteTireSet(tireSetId);
    }
}
