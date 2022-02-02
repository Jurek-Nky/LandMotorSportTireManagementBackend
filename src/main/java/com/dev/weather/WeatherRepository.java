package com.dev.weather;

import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends CrudRepository<Weather, Long> {
    List<Weather> findByWeatherConditionsAndRace_SelectedIsTrue(String weatherConditions);

    List<Weather> findWeathersByRace_RaceID(Long raceid);

    Optional<Weather> findWeatherByWetterid(Long weatherid);

    Optional<Weather> findFirstByRace_SelectedIsTrueOrderByTimeDesc();

    List<Weather> findByRace_RaceID(Long raceID);


}
