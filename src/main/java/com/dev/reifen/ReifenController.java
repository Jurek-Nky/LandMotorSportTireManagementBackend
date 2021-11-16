package com.dev.reifen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reifen")
// manages all API calls and sends responses
public class ReifenController {

    private final ReifenService reifenService;

    @Autowired
    public ReifenController(ReifenService reifenService) {
        this.reifenService = reifenService;
    }

    @GetMapping
    public List<Reifen> getAllReifen() {
        return reifenService.getReifen();
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
