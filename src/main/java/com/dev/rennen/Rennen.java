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
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long RennID;
    @Column(nullable = false)
    Date datum;
    @Column
    String ort;
    @OneToMany(mappedBy = "rennen")
    List<Reifen> reifen;
    @OneToMany(mappedBy = "rennen")
    List<Wetter> WetterDaten;

    public Rennen(Long rennID, Date datum, String ort) {
        RennID = rennID;
        this.datum = datum;
        this.ort = ort;
    }
    public Rennen(Date datum, String ort) {
        this.datum = datum;
        this.ort = ort;
    }

    public Rennen() {

    }

    public Long getRennID() {
        return RennID;
    }

    public void setRennID(Long rennID) {
        RennID = rennID;
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


}
