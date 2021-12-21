package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class TireManagementApi {
    //ReifenController rc = new ReifenController(new ReifenService());

    public static void main(String[] args) {
        SpringApplication.run(TireManagementApi.class, args);
    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));
    }
}