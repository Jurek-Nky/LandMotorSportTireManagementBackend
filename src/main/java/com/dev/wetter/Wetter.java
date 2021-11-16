package com.dev.wetter;

import com.dev.rennen.Rennen;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "wetter")
public class Wetter {
    @Id
    @SequenceGenerator(
            name = "wetter_sequence",
            sequenceName = "wetter_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "wetter_sequence"
    )
    Long wetterid;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "rennID")
    Rennen rennen;
    @Column(nullable = false)
    Time uhrzeit;
    @Column(nullable = false)
    int airtemperatur;
    @Column(nullable = false)
    int tracktemperatur;

    public Wetter() {
    }

    public Wetter(Time uhrzeit, Rennen rennen) {
        this.uhrzeit = uhrzeit;
        this.rennen = rennen;
    }

    public Wetter(Long wetterid, Time uhrzeit, Rennen rennen) {
        this.wetterid = wetterid;
        this.uhrzeit = uhrzeit;
        this.rennen = rennen;
    }

    public Long getWetterid() {
        return wetterid;
    }

    public void setWetterid(Long wetterid) {
        this.wetterid = wetterid;
    }

    public Rennen getRennen() {
        return rennen;
    }

    public void setRennen(Rennen rennen) {
        this.rennen = rennen;
    }

    public Time getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(Time uhrzeit) {
        this.uhrzeit = uhrzeit;
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
