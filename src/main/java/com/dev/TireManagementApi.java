package com.dev;

import com.dev.reifen.ReifenController;
import com.dev.reifen.ReifenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TireManagementApi {
    //ReifenController rc = new ReifenController(new ReifenService());

    public static void main(String[] args) {
        SpringApplication.run(TireManagementApi.class, args);
    }
}