package com.dev.rennen;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Argument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/rennen")
public class RennenController {

    private final RennenService rennenService;

    @Autowired
    public RennenController(RennenService rennenService) {
        this.rennenService = rennenService;
    }

    @GetMapping(path = "/all")
    public List<Rennen> getAllRennne() {
        return rennenService.getRennen();
    }

    @GetMapping(path = "/datum")
    public List<Rennen> getRennenByDate(@RequestAttribute(name = "date") String date) {
        return rennenService.findAllByDatum(date);
    }
}
