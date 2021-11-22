package com.dev.weather;

import com.dev.race.Race;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long wetterid;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "rennID")
    @JsonIgnore
    Race race;
    @Column(nullable = false)
    Time time;
    @Column(nullable = false)
    int airtemperatur;
    @Column(nullable = false)
    int tracktemperatur;

    public Weather() {
    }

    public Weather(Long wetterid, Race race, Time time, int airtemperatur, int tracktemperatur) {
        this.wetterid = wetterid;
        this.race = race;
        this.time = time;
        this.airtemperatur = airtemperatur;
        this.tracktemperatur = tracktemperatur;
    }

    public Weather(Race race, Time time, int airtemperatur, int tracktemperatur) {
        this.race = race;
        this.time = time;
        this.airtemperatur = airtemperatur;
        this.tracktemperatur = tracktemperatur;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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
}
