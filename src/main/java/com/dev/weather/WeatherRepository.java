package com.dev.weather;

import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends CrudRepository<Weather, Long> {

    List<Weather> findWeathersByDate(LocalDate localDate);

    List<Weather> findWeathersByRace_RaceID(Long raceid);

    Optional<Weather> findWeatherByWetterid(Long weatherid);

    List<Weather> findWeathersByWeatherConditions(String condition);

    List<Weather> findWeathersByTimeBetween(Time t1, Time t2);
}
