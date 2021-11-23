package com.dev.tire;

import com.dev.race.Race;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;

public class TireDto extends Tire {
    Long raceID;

    Optional<Double> bleed_in_blanket;
    Optional<Double> tp_hot1;
    Optional<Double> tp_hot2;
    Optional<Double> tp_hot3;
    Optional<Double> tp_hot4;
    Optional<Double> target;
    Optional<Double> bleed_hot1;
    Optional<Double> bleed_hot2;
    Optional<Double> bleed_hot3;
    Optional<Double> bleed_hot4;
    String abgegeben_fuer;

    public TireDto(Race race,
                   String serialNumber,
                   String bezeichnung,
                   LocalDate date,
                   Time time,
                   String spez,
                   String session,
                   double kaltdruck1,
                   double kaltdruck2,
                   double kaltdruck3,
                   double kaltdruck4,
                   int kaltdruckTemp,
                   int heatingTemp,
                   int heatingTime,
                   Time heatingStart,
                   Time heatingStop,
                   Long raceID,
                   Optional<Double> bleed_in_blanket,
                   Optional<Double> tp_hot1,
                   Optional<Double> tp_hot2,
                   Optional<Double> tp_hot3,
                   Optional<Double> tp_hot4,
                   Optional<Double> target,
                   Optional<Double> bleed_hot1,
                   Optional<Double> bleed_hot2,
                   Optional<Double> bleed_hot3,
                   Optional<Double> bleed_hot4,
                   String abgegeben_fuer) {
        super(null, serialNumber, bezeichnung, date, time, spez, session, kaltdruck1, kaltdruck2, kaltdruck3, kaltdruck4, kaltdruckTemp, heatingTemp, heatingTime, heatingStart, heatingStop);
        this.raceID = raceID;
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


    public Long getRaceID() {
        return raceID;
    }

    public void setRaceID(Long raceID) {
        this.raceID = raceID;
    }

}
