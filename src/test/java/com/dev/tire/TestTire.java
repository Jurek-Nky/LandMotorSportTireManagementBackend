package com.dev.tire;

import com.dev.race.Race;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

public class TestTire {
    private final Race race;
    private final String serialNumber;
    private final String bezeichnung;
    private final Time time;
    private final String session;
    private final double kaltdruck;
    private final int kaltdruckTemp;
    private final int heatingTemp;
    private final int heatingTime;
    private final Time heatingStart;
    private final Time heatingStop;
    private final String art;
    private final String mischung;
    private final Long tireId;
    Random rnd;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy:M:dd:hh:mm:ss");

    public TestTire(Race race) throws ParseException {
        mischung = rndString(10);
        art = rndString(10);
        rnd = new Random();
        tireId = rnd.nextLong(100000);
        this.race = race;
        serialNumber = "serial" + rnd.nextInt(10000);
        bezeichnung = rndString(8);
        time = new Time(sdf.parse(String.format("%s:%s:%s:%s:%s:%s",
                rnd.nextInt(1990, 2022),
                rnd.nextInt(12),
                rnd.nextInt(30),
                rnd.nextInt(23),
                rnd.nextInt(59),
                rnd.nextInt(59))).getTime());
        session = rndString(10);
        kaltdruck = rnd.nextDouble(10);
        kaltdruckTemp = rnd.nextInt(40);
        heatingTemp = rnd.nextInt(100);
        heatingTime = rnd.nextInt(100);
        heatingStart = new Time(rnd.nextInt(0, 23), rnd.nextInt(0, 59), rnd.nextInt(0, 59));
        heatingStop = new Time(rnd.nextInt(0, 23), rnd.nextInt(0, 59), rnd.nextInt(0, 59));

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
        return new Tire(race, serialNumber, bezeichnung, mischung,
                art, time, session, kaltdruck, kaltdruckTemp,
                heatingTemp, heatingTime, heatingStart, heatingStop);
    }

    public TireDto getTireDto() {
        return new TireDto(race.getRaceID(), serialNumber, bezeichnung,
                mischung, art);
    }
}
