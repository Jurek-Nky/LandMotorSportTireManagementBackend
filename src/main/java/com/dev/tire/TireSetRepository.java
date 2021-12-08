package com.dev.tire;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TireSetRepository extends CrudRepository<TireSet, Long> {
        Optional<TireSet> findFirstByRace_RaceIDEqualsOrderByTireSetNrDesc(Long raceID);
}
