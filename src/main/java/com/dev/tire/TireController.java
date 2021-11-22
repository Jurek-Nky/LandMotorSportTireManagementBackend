package com.dev.tire;

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
    public Optional<Tire> getReifenById(@RequestParam(name = "id") Long reifenId) {
        return tireService.findTireById(reifenId);
    }

    @GetMapping("/bez")
    public List<Tire> getReifenByBez(@RequestParam(name = "bez") String reifenBezeichnung) {
        return tireService.findTiresByBezeichnung(reifenBezeichnung);
    }

    @GetMapping("/serial")
    public Optional<Tire> getReifenBySerial(@RequestParam(name = "serial") String serialnumber) {
        return tireService.findTireBySerialnumber(serialnumber);
    }

    @GetMapping("/all")
    public List<Tire> getAllReifens() {
        return tireService.getTires();
    }

    @GetMapping("/race")
    public List<Tire> getReifensByRennen(@RequestParam(name = "rennid") Long rennid) {
        return tireService.findTiresByRennId(rennid);
    }
    // POST request could look something like this POST http://localhost:8080/api/v1/tire?raceid=2&serial=45678dff678&bez=nicer reifen&date=2021-06-03&time=5-5-5&spez=foo&session=bar&kd1=4&kd2=4&kd3=4&kd4=4&kdTemp=67&htTemp=57&htTime=94&htStart=7-9-9&htStop=9-9-9
    @PostMapping
    public Tire registerNewReifen(@RequestParam(required = true, name = "raceid") Long raceid,
                                  @RequestParam(required = true, name = "serial") String serialnumber,
                                  @RequestParam(required = true, name = "bez") String bezeichnung,
                                  @RequestParam(required = true, name = "date") String date,
                                  @RequestParam(required = true, name = "time") String time,
                                  @RequestParam(required = true, name = "spez") String spez,
                                  @RequestParam(required = true, name = "session") String session,
                                  @RequestParam(required = true, name = "kd1") Double kaltdruck1,
                                  @RequestParam(required = true, name = "kd1") Double kaltdruck2,
                                  @RequestParam(required = true, name = "kd3") Double kaltdruck3,
                                  @RequestParam(required = true, name = "kd3") Double kaltdruck4,
                                  @RequestParam(required = true, name = "kdTemp") int kaltdruckTemp,
                                  @RequestParam(required = true, name = "htTemp") int heatingTemp,
                                  @RequestParam(required = true, name = "htTime") int heatingTime,
                                  @RequestParam(required = true, name = "htStart") String heatingStart,
                                  @RequestParam(required = true, name = "htStop") String heatingStop,
                                  @RequestParam(required = false, name = "tp_hot1") Optional<Double> tp_hot1,
                                  @RequestParam(required = false, name = "tp_hot2") Optional<Double> tp_hot2,
                                  @RequestParam(required = false, name = "tp_hot3") Optional<Double> tp_hot3,
                                  @RequestParam(required = false, name = "tp_hot4") Optional<Double> tp_hot4,
                                  @RequestParam(required = false, name = "bleed_hot1") Optional<Double> bleed_hot1,
                                  @RequestParam(required = false, name = "bleed_hot2") Optional<Double> bleed_hot2,
                                  @RequestParam(required = false, name = "bleed_hot3") Optional<Double> bleed_hot3,
                                  @RequestParam(required = false, name = "bleed_hot4") Optional<Double> bleed_hot4,
                                  @RequestParam(required = false, name = "abgegeben_fuer") String abgegeben_fuer,
                                  @RequestParam(required = false, name = "bleed_in_blanket") Optional<Double> bleed_in_blanket,
                                  @RequestParam(required = false, name = "target") Optional<Double> target

    ) {
        return tireService.addNewTire(raceid, serialnumber, bezeichnung, date, time, spez,
                session, kaltdruck1, kaltdruck2, kaltdruck3, kaltdruck4, kaltdruckTemp,
                heatingTemp, heatingTime, heatingStart, heatingStop, tp_hot1,
                tp_hot2, tp_hot3, tp_hot4, bleed_hot1, bleed_hot2, bleed_hot3,
                bleed_hot4, abgegeben_fuer, bleed_in_blanket, target);
    }

    @DeleteMapping(path = "{tireID}")
    public void deleteReifen(@PathVariable("tireID") Long reifenId) {
        tireService.deleteTire(reifenId);
    }

    @PutMapping(path = "{tireID}")
    // request can look someting like this: PUT http://localhost:8080/api/v1/tire/id/1?tp_hot1=5.7&spez=foo&datum=2021-11-20
    public void updateReifen(@PathVariable("tireID") Long reifenId,
                             @RequestParam(required = false, name = "bezeichnung") String bezeichnung,
                             @RequestParam(required = false, name = "datum") LocalDate datum,
                             @RequestParam(required = false, name = "tp_hot1") Optional<Double> tp_hot1,
                             @RequestParam(required = false, name = "tp_hot2") Optional<Double> tp_hot2,
                             @RequestParam(required = false, name = "tp_hot3") Optional<Double> tp_hot3,
                             @RequestParam(required = false, name = "tp_hot4") Optional<Double> tp_hot4,
                             @RequestParam(required = false, name = "bleed_hot1") Optional<Double> bleed_hot1,
                             @RequestParam(required = false, name = "bleed_hot2") Optional<Double> bleed_hot2,
                             @RequestParam(required = false, name = "bleed_hot3") Optional<Double> bleed_hot3,
                             @RequestParam(required = false, name = "bleed_hot4") Optional<Double> bleed_hot4,
                             @RequestParam(required = false, name = "abgegeben_fuer") String abgegeben_fuer,
                             @RequestParam(required = false, name = "bleed_in_blanket") Optional<Double> bleed_in_blanket,
                             @RequestParam(required = false, name = "heatingStart") Time heatingStart,
                             @RequestParam(required = false, name = "heatingStop") Time heatingStop,
                             @RequestParam(required = false, name = "heatingTime") Optional<Integer> heatingTime,
                             @RequestParam(required = false, name = "heatingTemp") Optional<Integer> heatingTemp,
                             @RequestParam(required = false, name = "kaltdruck1") Optional<Double> kaltdruck1,
                             @RequestParam(required = false, name = "kaltdruck2") Optional<Double> kaltdruck2,
                             @RequestParam(required = false, name = "kaltdruck3") Optional<Double> kaltdruck3,
                             @RequestParam(required = false, name = "kaltdruck4") Optional<Double> kaltdruck4,
                             @RequestParam(required = false, name = "kaltdruckTemp") Optional<Integer> kaltdruckTemp,
                             @RequestParam(required = false, name = "serial") String serialnumber,
                             @RequestParam(required = false, name = "spez") String spez,
                             @RequestParam(required = false, name = "target") Optional<Double> target,
                             @RequestParam(required = false, name = "uhrzeit") Time uhrzeit,
                             @RequestParam(required = false, name = "rennid") Long rennid) {

        tireService.updateTire(reifenId, bezeichnung, datum, tp_hot1, tp_hot2, tp_hot3, tp_hot4, bleed_hot1, bleed_hot2,
                bleed_hot3, bleed_hot4, bleed_in_blanket, abgegeben_fuer, heatingStart, heatingStop, heatingTemp, heatingTime,
                kaltdruck1, kaltdruck2, kaltdruck3, kaltdruck4, kaltdruckTemp, serialnumber, spez, target, uhrzeit, rennid);

    }
}
