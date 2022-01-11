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
    @Column()
    double length = 0;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Weather> weather;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    TireMixturePrefixes prefixes;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
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
  
    @Column()
    @JsonIgnore
    Time orderReady;
    @Column()
    int tireContingent;

    public Race() {

    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Time getOrderReady() {
        return orderReady;
    }

    public int getTireContingent() {
        return tireContingent;
    }

    public void setTireContingent(int tireContingent) {
        this.tireContingent = tireContingent;
    }

    public void decreaseTireContingent() {
        tireContingent = tireContingent - 1;
    }

    public void setOrderReady(Time orderReady) {
        this.orderReady = orderReady;
    }

    public Time getOrderReady() {
        return orderReady;
    }

    public int getTireContingent() {
        return tireContingent;
    }

    public void setTireContingent(int tireContingent) {
        this.tireContingent = tireContingent;
    }

    public void decreaseTireContingent() {
        tireContingent = tireContingent - 1;
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

    public TireMixturePrefixes getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(TireMixturePrefixes prefixes) {
        this.prefixes = prefixes;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
