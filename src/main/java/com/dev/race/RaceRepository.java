package com.dev.race;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRepository extends CrudRepository<Race, Long> {
    Optional<Race> findFirstByOrderByDateDescRaceIDDesc();

    Optional<Race> findRaceByRaceID(Long raceID);

    List<Race> findRacesByDate(LocalDate date);
}
