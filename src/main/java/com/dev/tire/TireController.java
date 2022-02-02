package com.dev.tire;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/tire")
// manages all API calls and sends responses
public class TireController {

    private final TireService tireService;

    @Autowired
    public TireController(TireService tireService) {
        this.tireService = tireService;
    }

    @GetMapping("/id")
    @ApiOperation(value = "find tire by id.")
    public Optional<Tire> gettireById(@RequestParam(name = "id") Long reifenId) {
        return tireService.findTireById(reifenId);
    }

    @GetMapping("/ordertimer")
    public String getOrderTimer() {
        return tireService.getOrderTimer();
    }

    @PostMapping("/ordertimer")
    public Timestamp setOrderTimer(@RequestParam(name = "time") int minutes) {
        return tireService.setOrderTimer(minutes);
    }

    @PutMapping("/update/{tireID}")
    @ApiOperation(value = "update tire by id.")
    // request can look someting like this: PUT http://localhost:8080/api/v1/tire/id/1?tp_hot1=5.7&spez=foo&date=2021-11-20
    public Tire updateTire(@PathVariable("tireID") Long tireid,
                           @RequestParam(required = false, name = "serial") String serialnumber,
                           @RequestParam(required = false, name = "bezeichnung") String bezeichnung,
                           @RequestParam(required = false, name = "mischung") String mischung,
                           @RequestParam(required = false, name = "art") String art,
                           @RequestParam(required = false, name = "erhalten") Time time,
                           @RequestParam(required = false, name = "session") String session,
                           @RequestParam(required = false, name = "ll") Optional<Integer> laufleistung,
                           @RequestParam(required = false, name = "kaltdruck") Optional<Double> kaltdruck,
                           @RequestParam(required = false, name = "kt") Optional<Integer> kaltdruckTemp,
                           @RequestParam(required = false, name = "ktm") Optional<Integer> kaltdruckTempMeasured,
                           @RequestParam(required = false, name = "km") Optional<Double> kaltdruckModified,
                           @RequestParam(required = false, name = "bleed") boolean bleeded,
                           @RequestParam(required = false, name = "heatingTime") Optional<Integer> heatingTime,
                           @RequestParam(required = false, name = "heatingTemp") Optional<Integer> heatingTemp,
                           @RequestParam(required = false, name = "start") Time heatingStart,
                           @RequestParam(required = false, name = "stop") Time heatingStop) {

        return tireService.updateTire(tireid, serialnumber,
                bezeichnung, mischung, art, time, session,
                laufleistung, kaltdruck, kaltdruckTemp, kaltdruckTempMeasured, kaltdruckModified, bleeded, heatingTemp, heatingTime,
                heatingStart, heatingStop);
    }

    @PutMapping("/update/{id}/modification")
    public void updateModification(@PathVariable("id") Long tireID,
                                   @RequestParam("mod") String modification) {
        tireService.updateModification(tireID, modification);
    }

}
