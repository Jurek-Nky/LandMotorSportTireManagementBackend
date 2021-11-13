package com.dev.reifen;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
// ReifenRepository is the lowest layer of the Java backend and is used to interact with the database
public interface ReifenRepository extends CrudRepository<Reifen, Integer> {

}
