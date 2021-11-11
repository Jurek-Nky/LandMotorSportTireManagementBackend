package com.dev.reifen;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReifenRepository extends CrudRepository<Reifen, Integer> {

}
