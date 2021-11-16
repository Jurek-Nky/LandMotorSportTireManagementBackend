package com.dev.rennen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class RennenService {

    private final RennenRepository rennenRepository;

    @Autowired
    public RennenService(RennenRepository rennenRepository){
        this.rennenRepository = rennenRepository;
    }

    public List<Rennen> getRennen() {
        return (List<Rennen>) rennenRepository.findAll();
    }

    public List<Rennen> findAllByDatum(String datum) {
        Date d = Date.valueOf("31");
        return rennenRepository.findAllByDatum(Date.valueOf(datum));
    }
}
