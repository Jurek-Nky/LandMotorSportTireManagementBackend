package com.dev.tire;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
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

    @GetMapping("/race")
    @ApiOperation(value = "find tires by race.")
    public List<Tire> gettiresByRennen(@RequestParam(name = "raceid") Long raceid) {
        return tireService.findTiresByRennId(raceid);
    }

    @GetMapping("/time")
    @ApiOperation(value = "find all tires by time of order.")
    public List<Tire> getTiresByTime(@RequestParam(name = "time") Time time) {
        return tireService.findTiresByTime(time);
    }


    @PostMapping(value = "/new")
    @ApiOperation(value = "insert new tire.")
    public Tire registerNewtire(@RequestBody(required = true) TireDto tireDto) {
        System.out.println("controller");
        return tireService.addNewTire(tireDto);
    }

    @DeleteMapping(path = "/delete/{tireID}")
    @ApiOperation(value = "delete tire by id.")
    public void deletetire(@PathVariable("tireID") Long reifenId) {
        tireService.deleteTire(reifenId);
    }

    @PutMapping(path = "/update/{tireID}")
    @ApiOperation(value = "update tire by id.")
    // request can look someting like this: PUT http://localhost:8080/api/v1/tire/id/1?tp_hot1=5.7&spez=foo&date=2021-11-20
    public Tire updateTire(@PathVariable("tireID") Long tireid,
                           @RequestParam(required = false, name = "raceid") Long raceid,
                           @RequestParam(required = false, name = "serial") String serialnumber,
                           @RequestParam(required = false, name = "bezeichnung") String bezeichnung,
                           @RequestParam(required = false, name = "mischung") String mischung,
                           @RequestParam(required = false, name = "art") String art,
                           @RequestParam(required = false, name = "erhalten") Time time,
                           @RequestParam(required = false, name = "session") String session,
                           @RequestParam(required = false, name = "kaltdruck") Optional<Double> kaltdruck,
                           @RequestParam(required = false, name = "temp") Optional<Integer> kaltdruckTemp,
                           @RequestParam(required = false, name = "heatingTime") Optional<Integer> heatingTime,
                           @RequestParam(required = false, name = "heatingTemp") Optional<Integer> heatingTemp,
                           @RequestParam(required = false, name = "start") Time heatingStart,
                           @RequestParam(required = false, name = "stop") Time heatingStop) {

        return tireService.updateTire(tireid, raceid, serialnumber,
                bezeichnung, mischung, art, time, session,
                kaltdruck, kaltdruckTemp, heatingTemp, heatingTime,
                heatingStart, heatingStop);
    }
}
