package com.dev.weather;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class WeatherDto {

    private LocalDate date;
    private Time time;
    private int airtemp;
    private int tracktemp;
    private String cond;

    public WeatherDto(int airTemp, int trackTemp, String condition) {
        this.date = LocalDate.now();
        this.time = Time.valueOf(LocalTime.now());
        this.airtemp = airtemp;
        this.tracktemp = tracktemp;
        this.cond = condition;
    }

    public LocalDate getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public int getAirtemp() {
        return airtemp;
    }

    public int getTracktemp() {
        return tracktemp;
    }

    public String getCond() {
        return cond;
    }
}
