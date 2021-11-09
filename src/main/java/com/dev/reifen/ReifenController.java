package com.dev.reifen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reifen")
public class ReifenController {

    private final ReifenService reifenService;

    @Autowired
    public ReifenController(ReifenService reifenService){
        this.reifenService = reifenService;
    }

    @GetMapping
    public List<Reifen> getReifen() {
        return reifenService.getReifen();
    }
}
