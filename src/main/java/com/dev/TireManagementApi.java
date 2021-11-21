package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TireManagementApi {
    //ReifenController rc = new ReifenController(new ReifenService());

    public static void main(String[] args) {
        SpringApplication.run(TireManagementApi.class, args);
    }
}