package com.dev.reifen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

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

    public void addNewReifen(Reifen reifen) {
        reifenRepository.save(reifen);
    }

    //@Query("SELECT r from Reifen r WHERE r.bezeichnung = ?1")
    public List<Reifen> findReifenByBezeichnung(String bezeichnung) {
        return reifenRepository.findReifensByBezeichnung(bezeichnung);
    }

    public Optional<Reifen> findReifenById(int id) {
        return reifenRepository.findReifenById(id);
    }

    public void deleteReifen(int reifenId) {
        if (!reifenRepository.existsById(reifenId)) {
            throw new IllegalStateException("reifen with id " + reifenId + " does not exist.");
        } else {
            reifenRepository.deleteById(reifenId);
        }
    }

    @Transactional
    public void updateReifen(int reifenId, String bezeichnung, Date datum) {
        Reifen reifen = reifenRepository.findById(reifenId).orElseThrow(() ->
                new IllegalStateException("reifen mit der ID " + reifenId + " does not exist."));
        if (bezeichnung != null && bezeichnung.length() > 0 && !reifen.bezeichnung.equals(bezeichnung)) {
            reifen.setBezeichnung(bezeichnung);
        }

        if (datum != null && reifen.getDatum() != datum) {
            reifen.setDatum(datum);
        }
    }
}
