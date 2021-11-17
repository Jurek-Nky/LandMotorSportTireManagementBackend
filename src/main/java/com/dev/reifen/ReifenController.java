package com.dev.reifen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/reifen")
// manages all API calls and sends responses
public class ReifenController {

    private final ReifenService reifenService;

    @Autowired
    public ReifenController(ReifenService reifenService) {
        this.reifenService = reifenService;
    }

    @GetMapping("/id")
    public Optional<Reifen> getReifenById(@RequestParam(name = "id") Long reifenId) {
        return reifenService.findReifenById(reifenId);
    }
    @GetMapping("/bez")
    public List<Reifen> getReifenByBez(@RequestParam(name = "bez") String reifenBezeichnung) {
        return reifenService.findReifensByBezeichnung(reifenBezeichnung);
    }
    @GetMapping("/serial")
    public Optional<Reifen> getReifenBySerial(@RequestParam(name = "serial") String serialnumber) {
        return reifenService.findReifenBySerialnumber(serialnumber);
    }

    @GetMapping("/all")
    public List<Reifen> getReifenById() {
        return reifenService.getReifen();
    }
    @GetMapping("/rennen")
    public List<Reifen> getReifensByRennen(@RequestParam(name = "rennid") Long rennid){
        return reifenService.findReifensByRennId(rennid);
    }



    @PostMapping
    public void registerNewReifen(@RequestBody Reifen reifen) {
        reifenService.addNewReifen(reifen);
    }

    @DeleteMapping(path = "{reifenId}")
    public void deleteReifen(@PathVariable("reifenId") int reifenId) {
        reifenService.deleteReifen(reifenId);
    }

    @PutMapping(path = "{reifenId}")
    public void updateReifen(@PathVariable("reifenId") int reifenId,
                             @RequestParam(required = false) String bezeichnung,
                             @RequestParam(required = false) Date datum) {
        reifenService.updateReifen(reifenId, bezeichnung, datum);

    }
}
