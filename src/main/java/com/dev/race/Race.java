package com.dev.race;


import com.dev.tire.TireSet;
import com.dev.weather.Weather;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long raceID;
    @Column(nullable = false)
    LocalDate date;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String location;

    @OneToMany(mappedBy = "race")
    @JsonIgnore
    List<Weather> weather;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    tireMixturePrefixes prefixes;

    @OneToMany(mappedBy = "race", cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    List<TireSet> tireSets;

    @Column(nullable = false)
    @JsonIgnore
    double pressureVar1;
    @Column(nullable = false)
    @JsonIgnore
    double pressureVar2;
    @Column(nullable = false)
    @JsonIgnore
    double pressureVar3;
    @Column(nullable = false)
    @JsonIgnore
    double pressureVar4;
    @Column(nullable = true)
    Time orderReady;

    public Race() {

    }

    public Race(LocalDate date, String name, String location) {
        this.date = date;
        this.name = name;
        this.location = location;
    }

    public Time getOrderReady() {
        return orderReady;
    }

    public void setOrderReady(Time orderReady) {
        this.orderReady = orderReady;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRaceID() {
        return raceID;
    }

    public void setRaceID(Long raceID) {
        this.raceID = raceID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TireSet> getTireSets() {
        return tireSets;
    }

    public void setTireSets(List<TireSet> tireSets) {
        this.tireSets = tireSets;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public double[] getPressureVars() {
        return new double[]{pressureVar1, pressureVar2, pressureVar3, pressureVar4};
    }

    @JsonIgnore
    public void setPressureVars(double[] pressureVars) {
        this.pressureVar1 = pressureVars[0];
        this.pressureVar2 = pressureVars[1];
        this.pressureVar3 = pressureVars[2];
        this.pressureVar4 = pressureVars[3];
    }

    public tireMixturePrefixes getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(tireMixturePrefixes prefixes) {
        this.prefixes = prefixes;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
