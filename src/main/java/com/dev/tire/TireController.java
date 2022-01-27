package com.dev.tire;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
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

    @GetMapping("/bez")
    @ApiOperation(value = "find tires by bezeichnung.")
    public List<Tire> gettireByBez(@RequestParam(name = "bez") String reifenBezeichnung) {
        return tireService.findTiresByBezeichnung(reifenBezeichnung);
    }

    @GetMapping("/serial")
    @ApiOperation(value = "find tire by serialnumber")
    public Optional<Tire> gettireBySerial(@RequestParam(name = "serial") String serialnumber) {
        return tireService.findTireBySerialnumber(serialnumber);
    }


    @GetMapping("/all")
    @ApiOperation(value = "returns all tires that are stored in the system.")
    public List<Tire> getAlltires() {
        return tireService.getTires();
    }


    @GetMapping("/time")
    @ApiOperation(value = "find all tires by time of order.")
    public List<Tire> getTiresByTime(@RequestParam(name = "time") Time time) {
        return tireService.findTiresByTime(time);
    }

    @GetMapping("/ordertimer")
    public Time getOrderTimer() {
        return tireService.getOrderTimer();
    }

    @PostMapping("/ordertimer")
    public Time setOrderTimer(@RequestParam(name = "time") int minutes) {
        return tireService.setOrderTimer(minutes);
    }

    @DeleteMapping(path = "/delete/{tireID}")
    @ApiOperation(value = "delete tire by id.")
    public void deletetire(@PathVariable("tireID") Long reifenId) {
        tireService.deleteTire(reifenId);
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
                kaltdruck, kaltdruckTemp, kaltdruckTempMeasured, kaltdruckModified, bleeded, heatingTemp, heatingTime,
                heatingStart, heatingStop);
    }

    @PutMapping("/update/{id}/modification")
    public void updateModification(@PathVariable("id") Long tireID,
                                   @RequestParam("mod") String modification) {
        tireService.updateModification(tireID, modification);
    }

}
