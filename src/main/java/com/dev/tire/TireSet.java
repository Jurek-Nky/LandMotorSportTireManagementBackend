package com.dev.tire;


import com.dev.race.Race;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "tiresets")
public class TireSet {
    @Column(nullable = false)
    String status;
    @Column()
    Time orderTimer;
    @ManyToOne
    @JoinColumn(name = "raceID")
    @JsonIgnore
    Race race;
    @Column(nullable = false)
    int tireSetNr;
    @OneToMany(cascade = CascadeType.ALL)
    List<Tire> tires;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    public TireSet() {

    }

    public TireSet(int tireSetNr) {
        this.tireSetNr = tireSetNr;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public List<Tire> getTires() {
        return tires;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getTireSetNr() {
        return tireSetNr;
    }

    public void setTireSetNr(int tireSetNr) {
        this.tireSetNr = tireSetNr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getOrderTimer() {
        return orderTimer;
    }

    public void setOrderTimer(Time orderTimer) {
        this.orderTimer = orderTimer;
    }

}
