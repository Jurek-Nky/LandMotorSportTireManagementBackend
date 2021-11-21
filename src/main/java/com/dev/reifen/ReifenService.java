package com.dev.reifen;

import com.dev.rennen.RennenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
// ReifenService is the layer between the API and the data management.
public class ReifenService {

    private final ReifenRepository reifenRepository;
    private final RennenRepository rennenRepository;

    @Autowired
    public ReifenService(ReifenRepository reifenRepository, RennenRepository rennenRepository) {
        this.reifenRepository = reifenRepository;
        this.rennenRepository = rennenRepository;
    }

    /*##########################################################################################################
     *  methodes for GET requests
     */
    public List<Reifen> getReifen() {
        List<Reifen> reifens = (List<Reifen>) reifenRepository.findAll();
        if (reifens.isEmpty()) {
            throw new IllegalStateException("No tires were found.");
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
        if (reifen.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with ReifenID: %s", reifenId));
        }
        return reifen;
    }

    public Optional<Reifen> findReifenBySerialnumber(String serialnumber) {
        Optional<Reifen> reifen = reifenRepository.findReifenBySerialNumber(serialnumber);
        if (reifen.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with Serialnumber: %s", serialnumber));
        }
        return reifen;
    }

    public List<Reifen> findReifensByRennId(Long rennid) {
        List<Reifen> reifens = reifenRepository.findReifensByRennen_Rennid(rennid);
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
    public void deleteReifen(Long reifenId) {
        if (!reifenRepository.existsById(reifenId)) {
            throw new IllegalStateException(String.format("reifen with id %s does not exist.", reifenId));
        } else {
            reifenRepository.deleteById(reifenId);
        }
    }

    /*##########################################################################################################
     *  methodes for PUT requests
     */

    @Transactional
    // This methode checks every given argument for existence and equality to the reifen field and replaces the reifen field if necessary
    public void updateReifen(Long reifenId, String bezeichnung, LocalDate datum, Optional<Double> tp_hot1, Optional<Double> tp_hot2, Optional<Double> tp_hot3,
                             Optional<Double> tp_hot4, Optional<Double> bleed_hot1, Optional<Double> bleed_hot2, Optional<Double> bleed_hot3, Optional<Double> bleed_hot4,
                             Optional<Double> bleed_in_blanket, String abgegeben_fuer, Time heatingStart, Time heatingStop, Optional<Integer> heatingTemp,
                             Optional<Integer> heatingTime, Optional<Double> kaltdruck1, Optional<Double> kaltdruck2, Optional<Double> kaltdruck3, Optional<Double> kaltdruck4,
                             Optional<Integer> kaltdruckTemp, String serialnumber, String spez, Optional<Double> target, Time uhrzeit, Long rennid) {
        Reifen reifen = reifenRepository.findReifenByReifenid(reifenId).orElseThrow(() ->
                new IllegalStateException(String.format("Reifen with id %s could not be found.", reifenId)));
        if (bezeichnung != null && bezeichnung.length() > 0 && !reifen.bezeichnung.equals(bezeichnung)) {
            reifen.setBezeichnung(bezeichnung);
        }
        if (abgegeben_fuer != null && abgegeben_fuer.length() > 0 && !Objects.equals(reifen.abgegeben_fuer, abgegeben_fuer)) {
            reifen.setAbgegeben_fuer(abgegeben_fuer);
        }
        if (serialnumber != null && serialnumber.length() > 0 && !reifen.serialNumber.equals(serialnumber)) {
            reifen.setSerialNumber(serialnumber);
        }
        if (spez != null && spez.length() > 0 && !reifen.spez.equals(spez)) {
            reifen.setSpez(spez);
        }
        if (datum != null && !reifen.datum.equals(datum)) {
            reifen.setDatum(datum);
        }
        if (tp_hot1.isPresent() && reifen.tp_hot1 != tp_hot1.get()) {
            reifen.setTp_hot1(tp_hot1.get());
        }
        if (tp_hot2.isPresent() && reifen.tp_hot2 != tp_hot2.get()) {
            reifen.setTp_hot2(tp_hot2.get());
        }
        if (tp_hot3.isPresent() && reifen.tp_hot3 != tp_hot3.get()) {
            reifen.setTp_hot3(tp_hot3.get());
        }
        if (tp_hot4.isPresent() && reifen.tp_hot4 != tp_hot4.get()) {
            reifen.setTp_hot4(tp_hot4.get());
        }
        if (bleed_hot1.isPresent() && reifen.bleed_hot1 != bleed_hot1.get()) {
            reifen.setBleed_hot1(bleed_hot1.get());
        }
        if (bleed_hot2.isPresent() && reifen.bleed_hot2 != bleed_hot2.get()) {
            reifen.setBleed_hot2(bleed_hot2.get());
        }
        if (bleed_hot3.isPresent() && reifen.bleed_hot3 != bleed_hot3.get()) {
            reifen.setBleed_hot3(bleed_hot3.get());
        }
        if (bleed_hot4.isPresent() && reifen.bleed_hot4 != bleed_hot4.get()) {
            reifen.setBleed_hot4(bleed_hot4.get());
        }
        if (bleed_in_blanket.isPresent() && reifen.bleed_in_blanket != bleed_in_blanket.get()) {
            reifen.setBleed_in_blanket(bleed_in_blanket.get());
        }
        if (heatingStart != null && reifen.heatingStart != heatingStart) {
            reifen.setHeatingStart(heatingStart);
        }
        if (heatingStop != null && reifen.heatingStop != heatingStop) {
            reifen.setHeatingStop(heatingStop);
        }
        if (heatingTemp.isPresent() && reifen.heatingTemp != heatingTemp.get()) {
            reifen.setHeatingTemp(heatingTemp.get());
        }
        if (heatingTime.isPresent() && reifen.heatingTime != heatingTime.get()) {
            reifen.setHeatingTime(heatingTime.get());
        }
        if (kaltdruck1.isPresent() && reifen.kaltdruck1 != kaltdruck1.get()) {
            reifen.setKaltdruck1(kaltdruck1.get());
        }
        if (kaltdruck2.isPresent() && reifen.kaltdruck2 != kaltdruck2.get()) {
            reifen.setKaltdruck2(kaltdruck2.get());
        }
        if (kaltdruck3.isPresent() && reifen.kaltdruck3 != kaltdruck3.get()) {
            reifen.setKaltdruck3(kaltdruck3.get());
        }
        if (kaltdruck4.isPresent() && reifen.kaltdruck4 != kaltdruck4.get()) {
            reifen.setKaltdruck4(kaltdruck4.get());
        }
        if (kaltdruckTemp.isPresent() && reifen.kaltdruckTemp != kaltdruckTemp.get()) {
            reifen.setKaltdruckTemp(kaltdruckTemp.get());
        }
        if (target.isPresent() && reifen.target != target.get()) {
            reifen.setTarget(target.get());
        }
        if (uhrzeit != null && !reifen.uhrzeit.equals(uhrzeit)) {
            reifen.setUhrzeit(uhrzeit);
        }
        if (rennid != null && !reifen.rennen.getRennid().equals(rennid)) {
            reifen.setRennen(rennenRepository.findRennenByRennid(rennid).orElseThrow(() -> new IllegalStateException(String.format("Rennen with id %s not found.", rennid))));
        }

    }
}

