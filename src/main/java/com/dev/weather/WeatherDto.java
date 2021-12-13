package com.dev.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@ApiModel
public class WeatherDto {

    private int airtemp;
    private int tracktemp;
    private final String cond;

    public WeatherDto(int airTemp, int trackTemp, String condition) {
        this.airtemp = airtemp;
        this.tracktemp = tracktemp;
        this.cond = condition;
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
