package com.dev.reifen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
// ReifenService is the layer between the API and the data management.
public class ReifenService {

    private final ReifenRepository reifenRepository;

    @Autowired
    public ReifenService(ReifenRepository reifenRepository) {
        this.reifenRepository = reifenRepository;
    }


    public List<Reifen> getReifen() {
        System.out.println("getReifen");
        return (List<Reifen>) reifenRepository.findAll();
    }

    public List<Reifen> getSpecificReifen(String name) {
        System.out.println("getSpecReifen");
        List<Reifen> reifen = null;
        for (Reifen r : reifenRepository.findAll()) {
            if (r.bezeichnung.equals(name)) {
                reifen.add(r);
            }
        }
        return reifen;
    }
}
