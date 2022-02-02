package com.dev.weather;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @GetMapping("/time")
    Weather getWeatherByTime(@RequestParam(name = "id") Long id) {
        return weatherService.getWeatherByWeatherid(id);
    }

    @GetMapping("/condition")
    List<Weather> getWeathersByCondition(@RequestParam(name = "condition") String condition) {
        return weatherService.getWeathersByCondition(condition);
    }

    @GetMapping("/race")
    List<Weather> getWeathersByRace(@RequestParam(name = "race") Long raceid) {
        return weatherService.getWeathersByRace(raceid);
    }

    @GetMapping("/timer")
    @ApiOperation(value = "returns the time, when the latest  weather was recorded.")
    Timestamp getTimer() {
        return weatherService.getTimer();
    }

    @PostMapping("/new")
    Weather addNewWeather(@RequestParam(name = "raceid", required = false) Long raceid,
                          @RequestBody(required = true) WeatherDto weatherdto
    ) {
        return weatherService.addNewWeather(weatherdto, raceid);
    }

    @DeleteMapping("/delete/{id}")
    void removeWeather(@PathVariable("id") Long id) {
        weatherService.deleteWeatherById(id);
    }

    @PutMapping("/update/{id}")
    Weather updateWeather(@PathVariable("id") Long id,
                          @RequestParam(name = "time") Timestamp time,
                          @RequestParam(name = "raceid") Long raceid,
                          @RequestParam(name = "airtemp") Optional<Integer> airTemp,
                          @RequestParam(name = "tracktemp") Optional<Integer> trackTemp,
                          @RequestParam(name = "condition") String condition) {
        return weatherService.updateWeatherById(id, raceid, time, airTemp, trackTemp, condition);
    }

}
