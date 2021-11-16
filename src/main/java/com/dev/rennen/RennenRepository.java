package com.dev.rennen;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RennenRepository extends CrudRepository<Rennen, Integer> {

    public List<Rennen> findAllByDatum(Date date);
}
