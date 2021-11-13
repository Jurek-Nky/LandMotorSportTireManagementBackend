package com.dev.reifen;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name = "reifen")
// Reifen is an entity that can be stored in a database.
public class Reifen {
    @Id
    @SequenceGenerator(
            name = "reifen_sequence",
            sequenceName = "reifen_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reifen_sequence"
    )
    int id;
    String bezeichnung;
    Date datum;
    Time uhrzeit;
    String spez;
    String session;
    //int kaltdruck[] = new int[4];
    int kaltdruck_temp;
    int heating_temp;
    int heating_time_minutes;
    Time heating_start;
    Time heating_stop;

    public Reifen(int id, String bezeichnung, Date datum, Time uhrzeit, String spez,
                  String session, double[] kaltdruck, int kaltdruck_temp, int heating_temp,
                  int heating_time_minutes, Time heating_start, Time heating_stop) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.spez = spez;
        this.session = session;
        //this.kaltdruck = kaltdruck;
        this.kaltdruck_temp = kaltdruck_temp;
        this.heating_temp = heating_temp;
        this.heating_time_minutes = heating_time_minutes;
        this.heating_start = heating_start;
        this.heating_stop = heating_stop;
    }

    public Reifen(String bezeichnung, Date datum, Time uhrzeit, String spez, String session,
                  double[] kaltdruck, int kaltdruck_temp, int heating_temp, int heating_time_minutes,
                  Time heating_start, Time heating_stop) {
        this.bezeichnung = bezeichnung;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.spez = spez;
        this.session = session;
        //this.kaltdruck = kaltdruck;
        this.kaltdruck_temp = kaltdruck_temp;
        this.heating_temp = heating_temp;
        this.heating_time_minutes = heating_time_minutes;
        this.heating_start = heating_start;
        this.heating_stop = heating_stop;
    }

    public Reifen() {

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

//    public double[] getKaltdruck() {
//        return kaltdruck;
//    }
//
//    public void setKaltdruck(double[] kaltdruck) {
//        this.kaltdruck = kaltdruck;
//    }

    public int getKaltdruck_temp() {
        return kaltdruck_temp;
    }

    public void setKaltdruck_temp(int kaltdruck_temp) {
        this.kaltdruck_temp = kaltdruck_temp;
    }

    public int getHeating_temp() {
        return heating_temp;
    }

    public void setHeating_temp(int heating_temp) {
        this.heating_temp = heating_temp;
    }

    public int getHeating_time_minutes() {
        return heating_time_minutes;
    }

    public void setHeating_time_minutes(int heating_time_minutes) {
        this.heating_time_minutes = heating_time_minutes;
    }

    public Time getHeating_start() {
        return heating_start;
    }

    public void setHeating_start(Time heating_start) {
        this.heating_start = heating_start;
    }

    public Time getHeating_stop() {
        return heating_stop;
    }

    public void setHeating_stop(Time heating_stop) {
        this.heating_stop = heating_stop;
    }
}
