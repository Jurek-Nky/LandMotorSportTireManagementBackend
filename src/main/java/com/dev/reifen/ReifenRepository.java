package com.dev.reifen;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.repository.CrudRepository;

public interface ReifenRepository extends CrudRepository<SecurityProperties.User, Integer> {

}
