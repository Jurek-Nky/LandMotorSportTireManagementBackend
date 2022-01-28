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
    @Column()
    Time benutztUm;
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
    int kaltdruckTempMeasured;
    @Column()
    double kaltdruckModified;
    @Column()
    boolean bleeded = false;
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
    @Column()
    int laufleistung;

    public Tire() {
    }

    public Tire(String mischung, String art, String modification, String position) {
        this.mischung = mischung;
        this.art = art;
        this.modification = modification;
        this.position = position;
    }

    public int getLaufleistung() {
        return laufleistung;
    }

    public void setLaufleistung(int laufleistung) {
        this.laufleistung = laufleistung;
    }

    public int getKaltdruckTempMeasured() {
        return kaltdruckTempMeasured;
    }

    public void setKaltdruckTempMeasured(int kaltdruckTempMeasured) {
        this.kaltdruckTempMeasured = kaltdruckTempMeasured;
    }

    public double getKaltdruckModified() {
        return kaltdruckModified;
    }

    public void setKaltdruckModified(double kaltdruckModified) {
        this.kaltdruckModified = kaltdruckModified;
    }

    public boolean isBleeded() {
        return bleeded;
    }

    public void setBleeded(boolean bleeded) {
        this.bleeded = bleeded;
    }

    public Long getTireID() {
        return tireID;
    }

    public void setTireID(Long tireID) {
        this.tireID = tireID;
    }

    public TireSet getTireSet() {
        return tireSet;
    }

    public void setTireSet(TireSet tireSet) {
        this.tireSet = tireSet;
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

    public Time getBestelltUm() {
        return bestelltUm;
    }

    public void setBestelltUm(Time bestelltUm) {
        this.bestelltUm = bestelltUm;
    }

    public Time getErhaltenUm() {
        return erhaltenUm;
    }

    public void setErhaltenUm(Time erhaltenUm) {
        this.erhaltenUm = erhaltenUm;
    }

    public Time getBenutztUm() {
        return benutztUm;
    }

    public void setBenutztUm(Time benutztUm) {
        this.benutztUm = benutztUm;
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
