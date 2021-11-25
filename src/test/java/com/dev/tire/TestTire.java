package com.dev.tire;

import com.dev.race.Race;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

public class TestTire {
    private final Race race;
    private final String abgegeben_fuer;
    private final double bleed_hot4;
    private final double bleed_hot3;
    private final double bleed_hot2;
    private final double target;
    private final double bleed_hot1;
    private final double tp_hot4;
    private final double tp_hot3;
    private final double tp_hot2;
    private final double tp_hot1;
    private final double bleed_in_blanket;
    private final Time heatingStop;
    private final Time heatingStart;
    private final int heatingTime;
    private final int heatingTemp;
    private final int kaltdruckTemp;
    private final double kaltdruck4;
    private final double kaltdruck3;
    private final double kaltdruck2;
    private final double kaltdruck1;
    private final String session;
    private final String spez;
    private final Time time;
    private final LocalDate date;
    private final String bezeichnung;
    private final String serialNumber;
    private final Long tireId;
    Random rnd;

    public TestTire(Race race) {
        rnd = new Random();
        tireId = rnd.nextLong(100000);
        this.race = race;
        serialNumber = "serial" + rnd.nextInt(10000);
        bezeichnung = rndString(8);
        date = LocalDate.of(rnd.nextInt(1, 12), rnd.nextInt(1, 12), rnd.nextInt(1, 12));
        time = new Time(rnd.nextInt(0, 23), rnd.nextInt(0, 59), rnd.nextInt(0, 59));
        spez = rndString(10);
        session = rndString(10);
        kaltdruck1 = rnd.nextDouble(10);
        kaltdruck2 = rnd.nextDouble(10);
        kaltdruck3 = rnd.nextDouble(10);
        kaltdruck4 = rnd.nextDouble(10);
        kaltdruckTemp = rnd.nextInt(40);
        heatingTemp = rnd.nextInt(100);
        heatingTime = rnd.nextInt(100);
        heatingStart = new Time(rnd.nextInt(0, 23), rnd.nextInt(0, 59), rnd.nextInt(0, 59));
        heatingStop = new Time(rnd.nextInt(0, 23), rnd.nextInt(0, 59), rnd.nextInt(0, 59));
        bleed_in_blanket = rnd.nextDouble(10);
        tp_hot1 = rnd.nextDouble(10);
        tp_hot2 = rnd.nextDouble(10);
        tp_hot3 = rnd.nextDouble(10);
        tp_hot4 = rnd.nextDouble(10);
        target = rnd.nextDouble(10);
        bleed_hot1 = rnd.nextDouble(10);
        bleed_hot2 = rnd.nextDouble(10);
        bleed_hot3 = rnd.nextDouble(10);
        bleed_hot4 = rnd.nextDouble(10);
        abgegeben_fuer = rndString(10);
    }

    static String rndString(int n) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder stringBuilder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (chars.length() * Math.random());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }

    public Tire getTire() {
        return new Tire(tireId, race, serialNumber, bezeichnung, date, time, spez, session,
                kaltdruck1, kaltdruck2, kaltdruck3, kaltdruck4, kaltdruckTemp,
                heatingTemp, heatingTime, heatingStart, heatingStop, bleed_in_blanket,
                tp_hot1, tp_hot2, tp_hot3, tp_hot4, target, bleed_hot1, bleed_hot2,
                bleed_hot3, bleed_hot4, abgegeben_fuer);
    }
    public TireDto getTireDto(){
        return new TireDto(race, serialNumber, bezeichnung, date, time,
                spez, session, kaltdruck1, kaltdruck2, kaltdruck3, kaltdruck4, kaltdruckTemp,
                heatingTemp, heatingTime, heatingStart, heatingStop, race.getRaceID(),
                Optional.of(bleed_in_blanket), Optional.of(tp_hot1), Optional.of(tp_hot2),
                Optional.of( tp_hot3), Optional.of(tp_hot4), Optional.of(target),
                Optional.of(bleed_hot1),Optional.of( bleed_hot2), Optional.of(bleed_hot3),
                Optional.of(bleed_hot4), abgegeben_fuer);
    }
}
