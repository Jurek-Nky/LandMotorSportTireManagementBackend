package com.dev.reifen;

import com.dev.rennen.Rennen;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name = "Reifen")
// Reifen is an entity that can be stored in a database.
public class Reifen {
    @Id
    @SequenceGenerator(
            name = "reifen_sequence",
            sequenceName = "reifen_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "reifen_sequence"
    )
    Long id;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "RennID")
    Rennen rennen;
    @Column(nullable = false)
    String bezeichnung;
    @Column(nullable = false)
    Date datum;
    @Column(nullable = false)
    Time uhrzeit;
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
    @Column(nullable = false)
    double bleed_in_blanket;
    double TP_hot1;
    double TP_hot2;
    double TP_hot3;
    double TP_hot4;
    double target;
    double bleed_hot1;
    double bleed_hot2;
    double bleed_hot3;
    double bleed_hot4;
    String abgegben_fuer;
    @Column(unique = true, nullable = false)
    String serialNumber;


    public Reifen(Long id, String bezeichnung, Date datum, Time uhrzeit, String spez, String session, double kaltdruck1, double kaltdruck2, double kaltdruck3, double kaltdruck4, int kaltdruckTemp, int heatingTemp, int heatingTime, Time heatingStart, Time heatingStop, double bleed_in_blanket, double TP_hot1, double TP_hot2, double TP_hot3, double TP_hot4, double target, double bleed_hot1, double bleed_hot2, double bleed_hot3, double bleed_hot4, String abgegben_fuer, String serialNumber) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
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
        this.TP_hot1 = TP_hot1;
        this.TP_hot2 = TP_hot2;
        this.TP_hot3 = TP_hot3;
        this.TP_hot4 = TP_hot4;
        this.target = target;
        this.bleed_hot1 = bleed_hot1;
        this.bleed_hot2 = bleed_hot2;
        this.bleed_hot3 = bleed_hot3;
        this.bleed_hot4 = bleed_hot4;
        this.abgegben_fuer = abgegben_fuer;
        this.serialNumber = serialNumber;
    }

    public Reifen(String bezeichnung, Date datum, Time uhrzeit, String spez, String session, double kaltdruck1, double kaltdruck2, double kaltdruck3, double kaltdruck4, int kaltdruckTemp, int heatingTemp, int heatingTime, Time heatingStart, Time heatingStop, double bleed_in_blanket, double TP_hot1, double TP_hot2, double TP_hot3, double TP_hot4, double target, double bleed_hot1, double bleed_hot2, double bleed_hot3, double bleed_hot4, String abgegben_fuer, String serialNumber) {
        this.bezeichnung = bezeichnung;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
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
        this.TP_hot1 = TP_hot1;
        this.TP_hot2 = TP_hot2;
        this.TP_hot3 = TP_hot3;
        this.TP_hot4 = TP_hot4;
        this.target = target;
        this.bleed_hot1 = bleed_hot1;
        this.bleed_hot2 = bleed_hot2;
        this.bleed_hot3 = bleed_hot3;
        this.bleed_hot4 = bleed_hot4;
        this.abgegben_fuer = abgegben_fuer;
        this.serialNumber = serialNumber;
    }

    public Reifen() {

    }

    public Reifen(String bezeichnung, Date datum, Time uhrzeit, String spez, String session, double kaltdruck1, double kaltdruck2, double kaltdruck3, double kaltdruck4, int kaltdruckTemp, int heatingTemp, int heatingTime, Time heatingStart, Time heatingStop) {
        this.bezeichnung = bezeichnung;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(Time uhrzeit) {
        this.uhrzeit = uhrzeit;
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

    public double getTP_hot1() {
        return TP_hot1;
    }

    public void setTP_hot1(double TP_hot1) {
        this.TP_hot1 = TP_hot1;
    }

    public double getTP_hot2() {
        return TP_hot2;
    }

    public void setTP_hot2(double TP_hot2) {
        this.TP_hot2 = TP_hot2;
    }

    public double getTP_hot3() {
        return TP_hot3;
    }

    public void setTP_hot3(double TP_hot3) {
        this.TP_hot3 = TP_hot3;
    }

    public double getTP_hot4() {
        return TP_hot4;
    }

    public void setTP_hot4(double TP_hot4) {
        this.TP_hot4 = TP_hot4;
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

    public String getAbgegben_fuer() {
        return abgegben_fuer;
    }

    public void setAbgegben_fuer(String abgegben_fuer) {
        this.abgegben_fuer = abgegben_fuer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
