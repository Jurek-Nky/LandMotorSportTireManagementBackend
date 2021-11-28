package com.dev.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    List<Weather> getAllWeather() {
        return weatherService.getAllWeather();
    }

    @GetMapping("/date")
    List<Weather> getAllWeatherByDate(@RequestParam(name = "date") LocalDate date) {
        return weatherService.getWeathersByDate(date);
    }

    @GetMapping("/time")
    Weather getWeatherByTime(@RequestParam(name = "id") Long id) {
        return weatherService.getWeatherByWeatherid(id);
    }

    @PostMapping("/new")
    Weather addNewWeather(@RequestBody(required = true) Weather weather) {
        return weatherService.addNewWeather(weather);
    }

    @DeleteMapping("/delete/{id}")
    void removeWeather(@PathVariable("id") Long id) {
        weatherService.deleteWeatherById(id);
    }

    @PutMapping("/update/{id}")
    Weather updateWeather(@PathVariable("id") Long id,
                          @RequestParam(name = "time") Time time,
                          @RequestParam(name = "date") LocalDate date,
                          @RequestParam(name = "raceid") Long raceid,
                          @RequestParam(name = "airtemp") Optional<Integer> airTemp,
                          @RequestParam(name = "tracktemp") Optional<Integer> trackTemp) {
        return weatherService.updateWeatherById(id,raceid,date,time,airTemp,trackTemp);
    }

}
