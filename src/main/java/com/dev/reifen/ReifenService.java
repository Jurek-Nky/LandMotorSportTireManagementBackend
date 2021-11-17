package com.dev.reifen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
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

    /*##########################################################################################################
     *  methodes for GET requests
     */
    public List<Reifen> getReifen() {
        List<Reifen> reifens = (List<Reifen>) reifenRepository.findAll();
        if (reifens.isEmpty()) {
            throw new IllegalStateException("No Tires were found.");
        }
        return reifens;
    }

    public List<Reifen> findReifensByBezeichnung(String bezeichnung) {
        List<Reifen> reifens = reifenRepository.findReifensByBezeichnung(bezeichnung);
        if (reifens.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with Bezeichnung: %s", bezeichnung));
        }
        return reifens;
    }

    public Optional<Reifen> findReifenById(Long reifenId) {
        Optional<Reifen> reifen = reifenRepository.findReifenByReifenid(reifenId);
        if (!reifen.isPresent()) {
            throw new IllegalStateException(String.format("No tires were found with ReifenID: %s", reifenId));
        }
        return reifen;
    }

    public Optional<Reifen> findReifenBySerialnumber(String serialnumber) {
        Optional<Reifen> reifen = reifenRepository.findReifenBySerialNumber(serialnumber);
        if (!reifen.isPresent()) {
            throw new IllegalStateException(String.format("No tires were found with Serialnumber: %s", serialnumber));
        }
        return reifen;
    }

    public List<Reifen> findReifensByRennId(Long rennid) {
        List<Reifen> reifens = reifenRepository.findReifenByRennen_Rennid(rennid);
        if (reifens.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with RennID: %s", rennid));
        }
        return reifens;
    }

    /*##########################################################################################################
     *  methodes for POST requests
     */
    public void addNewReifen(Reifen reifen) {
        reifenRepository.save(reifen);
    }

    /*##########################################################################################################
     *  methodes for DELETE requests
     */
    public void deleteReifen(int reifenId) {
        if (!reifenRepository.existsById(reifenId)) {
            throw new IllegalStateException("reifen with id " + reifenId + " does not exist.");
        } else {
            reifenRepository.deleteById(reifenId);
        }
    }

    /*##########################################################################################################
     *  methodes for PUT requests
     */
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

