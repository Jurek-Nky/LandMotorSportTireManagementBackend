package com.dev.rennen;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RennenRepository extends CrudRepository<Rennen, Integer> {

    public Optional<Rennen> findRennenByRennid(Long id);

    List<Rennen> findAllByDatum(Date date);
}
