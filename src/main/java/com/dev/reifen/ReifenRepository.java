package com.dev.reifen;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// ReifenRepository is the lowest layer of the Java backend and is used to interact with the database
public interface ReifenRepository extends CrudRepository<Reifen, Integer> {
    // Repostitory automatically implements SQL-Querys for the following functions
    List<Reifen> findReifensByBezeichnung(String bezeichnung);
    Optional<Reifen> findReifenById(int id);
}
