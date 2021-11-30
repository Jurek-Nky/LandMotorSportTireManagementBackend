package com.dev.tire;

import com.dev.race.Race;
import io.swagger.annotations.ApiModel;

import java.sql.Time;

@ApiModel(description = "Date transfer object for easy tire insertion.")
public class TireDto extends Tire {

    Long raceid;

    public TireDto(Long raceid, String serialNumber, String bezeichnung,
                   String mischung, String art) {
        super(null, serialNumber, bezeichnung, mischung, art);
        this.raceid = raceid;
    }

//    public TireDto(Long raceid, String serialNumber,
//                   String bezeichnung, String mischung, String art,
//                   Time erhalten_um, String session, double kaltdruck,
//                   int kaltdruckTemp, int heatingTemp, int heatingTime,
//                   Time heatingStart, Time heatingStop) {
//        super(null, serialNumber, bezeichnung, mischung, art,
//                erhalten_um, session, kaltdruck, kaltdruckTemp,
//                heatingTemp, heatingTime, heatingStart, heatingStop);
//        this.raceid = raceid;
//    }

    public Long getRaceid() {
        return raceid;
    }

    public void setRaceid(Long raceid) {
        this.raceid = raceid;
    }

}
