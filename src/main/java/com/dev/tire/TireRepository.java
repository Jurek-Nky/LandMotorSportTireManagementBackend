package com.dev.tire;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
// ReifenRepository is the lowest layer of the Java backend and is used to interact with the database
public interface TireRepository extends CrudRepository<Tire, Long> {
    // Repostitory automatically implements SQL-Querys for the following functions

    List<Tire> findTiresByBezeichnungAndTireSet_Race_SelectedIsTrue(String bezeichnung);

    Optional<Tire> findTireByTireID(Long tireID);

    Optional<Tire> findTireBySerialNumberAndTireSet_Race_SelectedIsTrue(String serialnumber);

    List<Tire> findByErhaltenUmAndTireSet_Race_SelectedIsTrue(Time erhaltenUm);
}
