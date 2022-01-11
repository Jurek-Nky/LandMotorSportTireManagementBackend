package com.dev.tire;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TireSetRepository extends CrudRepository<TireSet, Long> {
        List<TireSet> findByStatus(String status);
        Optional<TireSet> findByTires_TireSet_ID(Long ID);
        Optional<TireSet> findFirstByRace_RaceIDEqualsOrderByTireSetNrDesc(Long raceID);




}
