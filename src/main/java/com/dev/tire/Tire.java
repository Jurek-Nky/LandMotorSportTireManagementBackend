package com.dev.tire;

import com.dev.race.Race;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "tires")
// Tire is an entity that can be stored in a database.
public class Tire {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long tireID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "raceID")
    @JsonIgnoreProperties("tireProRace") // prevents infinite recursion in GET request
    Race race;
    @Column(unique = true, nullable = false)
    String serialNumber;
    @Column(nullable = false)
    String bezeichnung;
    @Column(nullable = false)
    LocalDate date;
    @Column(nullable = false)
    Time time;
    @Column(nullable = false)
    String spez;
    @Column(nullable = false)
    String session;
    @Column(nullable = false)
    double kaltdruck1;
    @Column(nullable = false)
    double kaltdruck2;
    @Column(nullable = false)
    double kaltdruck3;
    @Column(nullable = false)
    double kaltdruck4;
    @Column(nullable = false)
    int kaltdruckTemp;
    @Column(nullable = false)
    int heatingTemp;
    @Column(nullable = false)
    int heatingTime;
    @Column(nullable = false)
    Time heatingStart;
    @Column(nullable = false)
    Time heatingStop;
    @Column(nullable = true)
    double bleed_in_blanket;
    @Column(nullable = true)
    double tp_hot1;
    @Column(nullable = true)
    double tp_hot2;
    @Column(nullable = true)
    double tp_hot3;
    @Column(nullable = true)
    double tp_hot4;
    @Column(nullable = true)
    double target;
    @Column(nullable = true)
    double bleed_hot1;
    @Column(nullable = true)
    double bleed_hot2;
    @Column(nullable = true)
    double bleed_hot3;
    @Column(nullable = true)
    double bleed_hot4;
    @Column(nullable = true)
    String abgegeben_fuer;

    public Tire(Race race, String serialNumber, String bezeichnung,
                LocalDate date, Time time, String spez,
                String session, double kaltdruck1, double kaltdruck2,
                double kaltdruck3, double kaltdruck4, int kaltdruckTemp,
                int heatingTemp, int heatingTime, Time heatingStart,
                Time heatingStop) {
        this.race = race;
        this.serialNumber = serialNumber;
        this.bezeichnung = bezeichnung;
        this.date = date;
        this.time = time;
        this.spez = spez;
        this.session = session;
        this.kaltdruck1 = kaltdruck1;
        this.kaltdruck2 = kaltdruck2;
        this.kaltdruck3 = kaltdruck3;
        this.kaltdruck4 = kaltdruck4;
        this.kaltdruckTemp = kaltdruckTemp;
        this.heatingTemp = heatingTemp;
        this.heatingTime = heatingTime;
        this.heatingStart = heatingStart;
        this.heatingStop = heatingStop;
    }

    public Tire(Long tireID, Race race, String serialNumber, String bezeichnung,
                LocalDate date, Time time, String spez, String session,
                double kaltdruck1, double kaltdruck2, double kaltdruck3,
                double kaltdruck4, int kaltdruckTemp, int heatingTemp,
                int heatingTime, Time heatingStart, Time heatingStop,
                double bleed_in_blanket, double tp_hot1, double tp_hot2,
                double tp_hot3, double tp_hot4, double target, double bleed_hot1,
                double bleed_hot2, double bleed_hot3, double bleed_hot4, String abgegeben_fuer) {
        this.tireID = tireID;
        this.race = race;
        this.serialNumber = serialNumber;
        this.bezeichnung = bezeichnung;
        this.date = date;
        this.time = time;
        this.spez = spez;
        this.session = session;
        this.kaltdruck1 = kaltdruck1;
        this.kaltdruck2 = kaltdruck2;
        this.kaltdruck3 = kaltdruck3;
        this.kaltdruck4 = kaltdruck4;
        this.kaltdruckTemp = kaltdruckTemp;
        this.heatingTemp = heatingTemp;
        this.heatingTime = heatingTime;
        this.heatingStart = heatingStart;
        this.heatingStop = heatingStop;
        this.bleed_in_blanket = bleed_in_blanket;
        this.tp_hot1 = tp_hot1;
        this.tp_hot2 = tp_hot2;
        this.tp_hot3 = tp_hot3;
        this.tp_hot4 = tp_hot4;
        this.target = target;
        this.bleed_hot1 = bleed_hot1;
        this.bleed_hot2 = bleed_hot2;
        this.bleed_hot3 = bleed_hot3;
        this.bleed_hot4 = bleed_hot4;
        this.abgegeben_fuer = abgegeben_fuer;
    }

    public Tire() {

    }

    public Long getTireID() {
        return tireID;
    }

    public void setTireID(Long id) {
        this.tireID = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getSpez() {
        return spez;
    }

    public void setSpez(String spez) {
        this.spez = spez;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public double getKaltdruck1() {
        return kaltdruck1;
    }

    public void setKaltdruck1(double kaltdruck1) {
        this.kaltdruck1 = kaltdruck1;
    }

    public double getKaltdruck2() {
        return kaltdruck2;
    }

    public void setKaltdruck2(double kaltdruck2) {
        this.kaltdruck2 = kaltdruck2;
    }

    public double getKaltdruck3() {
        return kaltdruck3;
    }

    public void setKaltdruck3(double kaltdruck3) {
        this.kaltdruck3 = kaltdruck3;
    }

    public double getKaltdruck4() {
        return kaltdruck4;
    }

    public void setKaltdruck4(double kaltdruck4) {
        this.kaltdruck4 = kaltdruck4;
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

    public double getBleed_in_blanket() {
        return bleed_in_blanket;
    }

    public void setBleed_in_blanket(double bleed_in_blanket) {
        this.bleed_in_blanket = bleed_in_blanket;
    }

    public double getTp_hot1() {
        return tp_hot1;
    }

    public void setTp_hot1(double tp_hot1) {
        this.tp_hot1 = tp_hot1;
    }

    public double getTp_hot2() {
        return tp_hot2;
    }

    public void setTp_hot2(double tp_hot2) {
        this.tp_hot2 = tp_hot2;
    }

    public double getTp_hot3() {
        return tp_hot3;
    }

    public void setTp_hot3(double tp_hot3) {
        this.tp_hot3 = tp_hot3;
    }

    public double getTp_hot4() {
        return tp_hot4;
    }

    public void setTp_hot4(double tp_hot4) {
        this.tp_hot4 = tp_hot4;
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getBleed_hot1() {
        return bleed_hot1;
    }

    public void setBleed_hot1(double bleed_hot1) {
        this.bleed_hot1 = bleed_hot1;
    }

    public double getBleed_hot2() {
        return bleed_hot2;
    }

    public void setBleed_hot2(double bleed_hot2) {
        this.bleed_hot2 = bleed_hot2;
    }

    public double getBleed_hot3() {
        return bleed_hot3;
    }

    public void setBleed_hot3(double bleed_hot3) {
        this.bleed_hot3 = bleed_hot3;
    }

    public double getBleed_hot4() {
        return bleed_hot4;
    }

    public void setBleed_hot4(double bleed_hot4) {
        this.bleed_hot4 = bleed_hot4;
    }

    public String getAbgegeben_fuer() {
        return abgegeben_fuer;
    }

    public void setAbgegeben_fuer(String abgegben_fuer) {
        this.abgegeben_fuer = abgegben_fuer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Tire{" +
                "reifenid=" + tireID +
                ", rennid= " + race.getRaceID() +
                ", serialNumber='" + serialNumber + '\'' +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", spez='" + spez + '\'' +
                ", session='" + session + '\'' +
                ", kaltdruck1=" + kaltdruck1 +
                ", kaltdruck2=" + kaltdruck2 +
                ", kaltdruck3=" + kaltdruck3 +
                ", kaltdruck4=" + kaltdruck4 +
                ", kaltdruckTemp=" + kaltdruckTemp +
                ", heatingTemp=" + heatingTemp +
                ", heatingTime=" + heatingTime +
                ", heatingStart=" + heatingStart +
                ", heatingStop=" + heatingStop +
                ", bleed_in_blanket=" + bleed_in_blanket +
                ", tp_hot1=" + tp_hot1 +
                ", tp_hot2=" + tp_hot2 +
                ", tp_hot3=" + tp_hot3 +
                ", tp_hot4=" + tp_hot4 +
                ", target=" + target +
                ", bleed_hot1=" + bleed_hot1 +
                ", bleed_hot2=" + bleed_hot2 +
                ", bleed_hot3=" + bleed_hot3 +
                ", bleed_hot4=" + bleed_hot4 +
                ", abgegeben_fuer='" + abgegeben_fuer + '\'' +
                '}';
    }
}
