package com.dev.race;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRepository extends CrudRepository<Race, Integer> {

    Optional<Race> findRaceByRaceID(Long raceID);

    List<Race> findRacesByDate(Date date);
}
