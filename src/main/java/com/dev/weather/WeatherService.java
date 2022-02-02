package com.dev.weather;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
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

    public Weather getWeatherByWeatherid(Long weatherid) {
        Optional<Weather> weather = weatherRepository.findWeatherByWetterid(weatherid);
        if (weather.isEmpty()) {
            throw new IllegalStateException(String.format("No weather with id %s was found.", weatherid));
        }
        return weather.get();
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

    public List<Weather> getWeathersByCondition(String condition) {
        List<Weather> weathers = weatherRepository.findWeathersByWeatherConditions(condition);
        if (weathers.isEmpty()) {
            throw new IllegalStateException(String.format("No weather with condition %s was found.", condition));
        }
        return weathers;
    }

    public List<Weather> getWeathersByFilter(Long raceid, Timestamp time, Optional<Integer> airtemp, Optional<Integer> tracktemp, String cond) {
        if (raceid == null && cond == null && time == null && airtemp.isEmpty() && tracktemp.isEmpty()) {
            throw new IllegalStateException("No parameter specified");
        }
        List<Weather> weathers = (List<Weather>) weatherRepository.findAll();

        if (raceid != null) {
            weathers.removeIf(weather -> weather.getRace().getRaceID() != raceid);
        }

        if (time != null) {
            weathers.removeIf(weather -> weather.getTime() != time);
        }
        if (airtemp.isPresent()) {
            weathers.removeIf(weather -> weather.getAirtemperatur() != airtemp.get());
        }
        if (tracktemp.isPresent()) {
            weathers.removeIf(weather -> weather.getTracktemperatur() != tracktemp.get());
        }
        if (cond != null && cond.length() > 0) {
            weathers.removeIf(weather -> !weather.getWeatherConditions().equals(cond));
        }


        if (weathers.isEmpty()) {
            throw new IllegalStateException(String.format("No weather found with raceid: %s" +
                            ", date: %s, time: %s,airtemperature: %s" +
                            ",trackTemperature: %s and condition: %s.",
                    raceid, time, airtemp, tracktemp, cond));
        }
        return weathers;
    }

    public Weather addNewWeather(WeatherDto weatherDto, Long raceid) {
        Optional<Race> race;
        if (raceid != null) {
            race = raceRepository.findRaceByRaceID(raceid);
            if (race.isEmpty()) {
                throw new IllegalStateException(String.format("No race with id %s was found.", raceid));
            }
        } else {
            race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
            if (race.isEmpty()) {
                throw new IllegalStateException("No race is availiable.");
            }
        }
        Weather weather = new Weather();
        weather.setTime(new Timestamp(System.currentTimeMillis()));
        weather.setWeatherConditions(weatherDto.getCond());
        weather.setRace(race.get());
        weather.setAirtemperatur(weatherDto.getAirtemp());
        weather.setTracktemperatur(weatherDto.getTracktemp());
        return weatherRepository.save(weather);
    }

    public void deleteWeatherById(Long weatherid) {
        if (weatherRepository.findWeatherByWetterid(weatherid).isEmpty()) {
            throw new IllegalStateException(String.format("No weather with id %s was found.", weatherid));
        }
        weatherRepository.deleteById(weatherid);
    }

    @Transactional
    public Weather updateWeatherById(Long weatherid, Long raceid, Timestamp time, Optional<Integer> airTemp, Optional<Integer> trackTemp, String condition) {
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
        if (time != null && time != weather.get().getTime()) {
            weather.get().setTime(time);
        }
        if (airTemp.isPresent() && airTemp.get() != weather.get().getAirtemperatur()) {
            weather.get().setAirtemperatur(airTemp.get());
        }
        if (trackTemp.isPresent() && trackTemp.get() != weather.get().getTracktemperatur()) {
            weather.get().setTracktemperatur(trackTemp.get());
        }
        if (condition != null && condition.length() > 0 && !weather.get().weatherConditions.equals(condition)) {
            weather.get().setWeatherConditions(condition);
        }

        return weather.get();
    }

    public Timestamp getTimer() {
        Optional<Weather> latest = weatherRepository.findFirstByOrderByTimeDesc();
        if (latest.isEmpty()) {
            return null;
        }
        return latest.get().getTime();
    }
}
