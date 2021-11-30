package com.dev.weather;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final RaceRepository raceRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository, RaceRepository raceRepository) {
        this.weatherRepository = weatherRepository;
        this.raceRepository = raceRepository;
    }

    public List<Weather> getAllWeather() {
        List<Weather> weathers = (List<Weather>) weatherRepository.findAll();
        if (weathers.isEmpty()) {
            throw new IllegalStateException("No weathers were found.");
        }
        return weathers;
    }

    public Weather getWeatherByWeatherid(Long weatherid){
        Optional<Weather> weather = weatherRepository.findWeatherByWetterid(weatherid);
        if (weather.isEmpty()){
            throw new IllegalStateException(String.format("No weather with id %s was found.",weatherid));
        }
        return weather.get();
    }

    public List<Weather> getWeathersByDate(LocalDate localDate) {
        List<Weather> weathers = weatherRepository.findWeathersByDate(localDate);
        if (weathers.isEmpty()) {
            throw new IllegalStateException(String.format("No weathers with date %s were found.", localDate));
        }
        return weathers;
    }

    public List<Weather> getWeathersByRace(Long raceid) {
        Optional<Race> race = raceRepository.findRaceByRaceID(raceid);
        if (race.isEmpty()) {
            throw new IllegalStateException(String.format("Race with raceid %s could not be found.", raceid));
        }
        List<Weather> weathers = weatherRepository.findWeathersByRace_RaceID(raceid);
        if (weathers.isEmpty()) {
            throw new IllegalStateException(String.format("No Weathers were found with RaceId %s", raceid));
        }
        return weathers;
    }

    public Weather addNewWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public void deleteWeatherById(Long weatherid) {
        if (weatherRepository.findWeatherByWetterid(weatherid).isEmpty()) {
            throw new IllegalStateException(String.format("No weather with id %s was found.", weatherid));
        }
        weatherRepository.deleteById(weatherid);
    }

    @Transactional
    public Weather updateWeatherById(Long weatherid, Long raceid, LocalDate localDate, Time time, Optional<Integer> airTemp, Optional<Integer> trackTemp) {
        Optional<Weather> weather = weatherRepository.findWeatherByWetterid(weatherid);
        if (weather.isEmpty()) {
            throw new IllegalStateException(String.format("No weather with id %s was found.", weatherid));
        }
        Optional<Race> race = raceRepository.findRaceByRaceID(raceid);
        if (race.isEmpty()) {
            throw new IllegalStateException(String.format("No race with id %s was found.", raceid));
        }
        if (weather.get().getRace() != weather.get().getRace()) {
            weather.get().setRace(race.get());
        }
        if (localDate != null && localDate != weather.get().getDate()) {
            weather.get().setDate(localDate);
        }
        if (time != null && time != weather.get().getTime()) {
            weather.get().setTime(time);
        }
        if (airTemp.isPresent() && airTemp.get() != weather.get().getAirtemperatur()) {
            weather.get().setAirtemperatur(airTemp.get());
        }
        if (trackTemp.isPresent() && trackTemp.get() != weather.get().getTracktemperatur()) {
            weather.get().setTracktemperatur(trackTemp.get());
        }

        return weather.get();
    }

}
