package com.dev.tire;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Date transfer object for easy tire insertion.")
public class TireDto {

    String frontArt;
    String frontMischung;
    String rearArt;
    String rearMischung;

    public TireDto(String frontArt, String frontMischung, String rearArt, String rearMischung) {
        this.frontArt = frontArt;
        this.frontMischung = frontMischung;
        this.rearArt = rearArt;
        this.rearMischung = rearMischung;
    }

    public String getFrontArt() {
        return frontArt;
    }

    public void setFrontArt(String frontArt) {
        this.frontArt = frontArt;
    }

    public String getFrontMischung() {
        return frontMischung;
    }

    public void setFrontMischung(String frontMischung) {
        this.frontMischung = frontMischung;
    }

    public String getRearArt() {
        return rearArt;
    }

    public void setRearArt(String rearArt) {
        this.rearArt = rearArt;
    }

    public String getRearMischung() {
        return rearMischung;
    }

    public void setRearMischung(String rearMischung) {
        this.rearMischung = rearMischung;
    }
}
