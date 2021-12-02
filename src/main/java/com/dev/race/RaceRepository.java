package com.dev.race;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRepository extends CrudRepository<Race, Long> {

    Optional<Race> findRaceByRaceID(Long raceID);

    List<Race> findRacesByDate(Date date);

    Optional<Race> getFirstByOrderByDateDesc();
}
