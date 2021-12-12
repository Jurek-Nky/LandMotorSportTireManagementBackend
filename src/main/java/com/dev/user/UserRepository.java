package com.dev.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findUserByFirstName(String firstName);

    boolean existsUserByRole_RoleName(String rolename);
}