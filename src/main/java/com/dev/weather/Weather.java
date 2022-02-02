package com.dev.weather;

import com.dev.race.Race;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "weathers")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long wetterid;
    @ManyToOne()
    @JoinColumn(name = "raceID")
    @JsonIgnoreProperties({"tireProRace", "weather"})
    @JsonIgnore
    Race race;
    @Column(nullable = false)
    Timestamp time;
    @Column(nullable = false)
    int airtemperatur;
    @Column(nullable = false)
    int tracktemperatur;
    @Column(nullable = false)
    String weatherConditions;

    public Weather() {
    }

    public Weather(Race race, Timestamp time, int airtemperatur, int tracktemperatur, String condition) {
        this.race = race;
        this.time = time;
        this.airtemperatur = airtemperatur;
        this.tracktemperatur = tracktemperatur;
        this.weatherConditions = condition;
    }

    public Long getWetterid() {
        return wetterid;
    }

    public void setWetterid(Long wetterid) {
        this.wetterid = wetterid;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getAirtemperatur() {
        return airtemperatur;
    }

    public void setAirtemperatur(int airtemperatur) {
        this.airtemperatur = airtemperatur;
    }

    public int getTracktemperatur() {
        return tracktemperatur;
    }

    public void setTracktemperatur(int tracktemperatur) {
        this.tracktemperatur = tracktemperatur;
    }

    public String getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(String weatherConditions) {
        this.weatherConditions = weatherConditions;
    }
}
