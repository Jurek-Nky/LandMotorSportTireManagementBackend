package com.dev.reifen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class ReifenService {

    private final ReifenRepository reifenRepository;

    @Autowired
    public ReifenService(ReifenRepository reifenRepository) {
        this.reifenRepository = reifenRepository;
    }


    public List<Reifen> getReifen() {
        System.out.println("getReifen");
        return (List<Reifen>) reifenRepository.findAll();

        //return List.of(new Reifen("298(6ns)", new Date(2021, 6, 2), new Time(0), "med G/D", "Q2", new double[]{1.49, 1.33, 1.52, 1.37}, 20, 90, 90, new Time(18, 30, 0), new Time(20, 0, 0)));
    }

    public List<Reifen> getSpecReifen(String name) {
        System.out.println("getSpecReifen");
        List<Reifen> reifen = null;
        for (Reifen r : reifenRepository.findAll()){
            if (r.bezeichnung.equals(name)){
                reifen.add(r);
            }
        }
        return reifen;
    }
}
