package com.dev.tire;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "tires")
// Tire is an entity that can be stored in a database.
public class Tire {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long tireID;

    @ManyToOne
    @JsonIgnore
    TireSet tireSet;
    //@Column(unique = true, nullable = false)
    // just for testing purposes. in a normal usecase serialnumber should always be unique
    @Column()
    String serialNumber;
    @Column()
    String bezeichnung;
    @Column()
    Time bestelltUm;
    @Column()
    Time erhaltenUm;
    @Column(nullable = false)
    String mischung;
    @Column(nullable = false)
    String art;
    @Column()
    String session;
    @Column()
    double kaltdruck;
    @Column()
    int kaltdruckTemp;
    @Column()
    int heatingTemp;
    @Column()
    int heatingTime;
    @Column()
    Time heatingStart;
    @Column()
    Time heatingStop;
    @Column()
    String modification;
    @Column()
    String position;

    public TireSet getTireSet() {
        return tireSet;
    }

    public Tire(String mischung,
                String art) {
        this.mischung = mischung;
        this.art = art;
    }

    public Tire(String serialNumber,
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
    }

    public Tire(Long tireID, TireSet tireSet, String serialNumber, String bezeichnung, Time bestelltUm, Time erhaltenUm, String mischung, String art, String session, double kaltdruck, int kaltdruckTemp, int heatingTemp, int heatingTime, Time heatingStart, Time heatingStop, String modification, String position) {
        this.tireID = tireID;
        this.tireSet = tireSet;
        this.serialNumber = serialNumber;
        this.bezeichnung = bezeichnung;
        this.bestelltUm = bestelltUm;
        this.erhaltenUm = erhaltenUm;
        this.mischung = mischung;
        this.art = art;
        this.session = session;
        this.kaltdruck = kaltdruck;
        this.kaltdruckTemp = kaltdruckTemp;
        this.heatingTemp = heatingTemp;
        this.heatingTime = heatingTime;
        this.heatingStart = heatingStart;
        this.heatingStop = heatingStop;
        this.modification = modification;
        this.position = position;
    }

    public Time getBestelltUm() {
        return bestelltUm;

    }

    public void setBestelltUm(Time bestelltUm) {
        this.bestelltUm = bestelltUm;
    }

    public void setTireSet(TireSet tireSet) {
        this.tireSet = tireSet;

    }

    public Tire() {
    }

    public Long getTireID() {
        return tireID;
    }

    public void setTireID(Long tireID) {
        this.tireID = tireID;
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

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
