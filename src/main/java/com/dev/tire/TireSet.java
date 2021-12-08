package com.dev.tire;


import com.dev.race.Race;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tiresets")
public class TireSet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "raceID")
    Race race;

    @Column(nullable = false)
    int tireSetNr;

    @OneToMany(mappedBy = "tireSet")
    @JsonIgnore
    List<Tire> tires;

    public Race getRace() {
        return race;
    }


    public List<Tire> getTires() {
        return tires;
    }

    public TireSet(Race race, Tire FL, Tire FR, Tire RL, Tire RR, int tireSetNr) {
        this.race = race;
        tires.add(FL);
        tires.add(FR);
        tires.add(RL);
        tires.add(RR);
        this.tireSetNr = tireSetNr;
    }

    public TireSet(int tireSetNr){
        this.tireSetNr = tireSetNr;
    }

    public TireSet() {

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public int getTireSetNr() {
        return tireSetNr;
    }

    public void setTireSetNr(int tireSetNr) {
        this.tireSetNr = tireSetNr;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }
}
