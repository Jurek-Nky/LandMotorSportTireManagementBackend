package com.dev.tire;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Date transfer object for easy tire insertion.")
public class TireDto {

    private Long raceid;
    private String serial;
    private String bezeichnung;
    private String mischung;
    private String art;

    public TireDto(Long raceid,
                   String serial,
                   String bezeichnung,
                   String mischung,
                   String art) {
        this.serial = serial;
        this.raceid = raceid;
        this.bezeichnung = bezeichnung;
        this.mischung = mischung;
        this.art = art;
    }

    public Long getRaceid() {
        return raceid;
    }

    public String getSerial() {
        return serial;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public String getMischung() {
        return mischung;
    }

    public String getArt() {
        return art;
    }
}
