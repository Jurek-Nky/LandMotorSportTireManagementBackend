package com.dev.rennen;

import com.dev.reifen.Reifen;
import com.dev.wetter.Wetter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Rennen")
public class Rennen {
    @Id
    @SequenceGenerator(
            name = "rennen_sequence",
            sequenceName = "rennen_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "rennen_sequence"
    )
    Long rennid;
    @Column(nullable = false)
    Date datum;
    @Column(nullable = false)
    String ort;
    @OneToMany(mappedBy = "rennen")
    List<Reifen> ReifenProRennen;
    @OneToMany(mappedBy = "rennen")
    List<Wetter> WetterDaten;

    public Rennen(Long rennid, Date datum, String ort) {
        rennid = rennid;
        this.datum = datum;
        this.ort = ort;
    }
    public Rennen(Date datum, String ort) {
        this.datum = datum;
        this.ort = ort;
    }

    public Rennen() {

    }

    public Long getRennid() {
        return rennid;
    }

    public void setRennid(Long rennid) {
        this.rennid = rennid;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public List<Reifen> getReifenProRennen() {
        return ReifenProRennen;
    }

    public void setReifenProRennen(List<Reifen> reifenProRennen) {
        ReifenProRennen = reifenProRennen;
    }

    public List<Wetter> getWetterDaten() {
        return WetterDaten;
    }

    public void setWetterDaten(List<Wetter> wetterDaten) {
        WetterDaten = wetterDaten;
    }
}
