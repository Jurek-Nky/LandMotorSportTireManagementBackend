package com.dev.tire;

import com.dev.race.Race;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "tires")
// Tire is an entity that can be stored in a database.
public class Tire {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long tireID;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "raceID")
    @JsonIgnoreProperties({"tireProRace", "weather"}) // prevents infinite recursion in GET request
    Race race;
    //@Column(unique = true, nullable = false)
    // just for testing purposes. in a normal usecase serialnumber should always be unique
    @Column(nullable = true)
    String serialNumber;
    @Column(nullable = false)
    String status;
    @Column(nullable = false)
    String bezeichnung;
    @Column(nullable = true)
    Time erhaltenUm;
    @Column(nullable = false)
    String mischung;
    @Column(nullable = false)
    String art;
    @Column(nullable = true)
    String session;
    @Column(nullable = true)
    double kaltdruck;
    @Column(nullable = true)
    int kaltdruckTemp;
    @Column(nullable = true)
    int heatingTemp;
    @Column(nullable = true)
    int heatingTime;
    @Column(nullable = true)
    Time heatingStart;
    @Column(nullable = true)
    Time heatingStop;

    public Tire(Race race,
                String serialNumber,
                String bezeichnung,
                String mischung,
                String art) {
        this.race = race;
        this.serialNumber = serialNumber;
        this.bezeichnung = bezeichnung;
        this.mischung = mischung;
        this.art = art;
        this.status = "bestellt";
    }

    public Tire(Race race,
                String serialNumber,
                String bezeichnung,
                String mischung,
                String art,
                Time erhalten_um,
                String session,
                double kaltdruck,
                int kaltdruckTemp,
                int heatingTemp,
                int heatingTime,
                Time heatingStart,
                Time heatingStop) {
        this.race = race;
        this.serialNumber = serialNumber;
        this.bezeichnung = bezeichnung;
        this.erhaltenUm = erhalten_um;
        this.mischung = mischung;
        this.art = art;
        this.session = session;
        this.kaltdruck = kaltdruck;
        this.kaltdruckTemp = kaltdruckTemp;
        this.heatingTemp = heatingTemp;
        this.heatingTime = heatingTime;
        this.heatingStart = heatingStart;
        this.heatingStop = heatingStop;
        this.status = "bestellt";
    }

    public Tire() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTireID() {
        return tireID;
    }

    public void setTireID(Long tireID) {
        this.tireID = tireID;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Time getErhaltenUm() {
        return erhaltenUm;
    }

    public void setErhaltenUm(Time erhalten_um) {
        this.erhaltenUm = erhalten_um;
    }

    public String getMischung() {
        return mischung;
    }

    public void setMischung(String mischung) {
        this.mischung = mischung;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public double getKaltdruck() {
        return kaltdruck;
    }

    public void setKaltdruck(double kaltdruck) {
        this.kaltdruck = kaltdruck;
    }

    public int getKaltdruckTemp() {
        return kaltdruckTemp;
    }

    public void setKaltdruckTemp(int kaltdruckTemp) {
        this.kaltdruckTemp = kaltdruckTemp;
    }

    public int getHeatingTemp() {
        return heatingTemp;
    }

    public void setHeatingTemp(int heatingTemp) {
        this.heatingTemp = heatingTemp;
    }

    public int getHeatingTime() {
        return heatingTime;
    }

    public void setHeatingTime(int heatingTime) {
        this.heatingTime = heatingTime;
    }

    public Time getHeatingStart() {
        return heatingStart;
    }

    public void setHeatingStart(Time heatingStart) {
        this.heatingStart = heatingStart;
    }

    public Time getHeatingStop() {
        return heatingStop;
    }

    public void setHeatingStop(Time heatingStop) {
        this.heatingStop = heatingStop;
    }
}
