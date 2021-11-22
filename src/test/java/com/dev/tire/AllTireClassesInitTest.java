package com.dev.tire;

import com.dev.race.RaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AllTireClassesInitTest {
    @Autowired
    private TireRepository tireRepository;
    @Autowired
    private RaceRepository raceRepository;
    @Autowired
    private TireService tireService = new TireService(tireRepository, raceRepository);
    @Autowired
    private TireController tireController = new TireController(tireService);

    @Test
    void repositoryIsNotNull() {
        assertThat(tireRepository).isNotNull();
    }

    @Test
    void serviceIsNotNull() {
        assertThat(tireService).isNotNull();
    }

    @Test
    void controllerIsNotNull() {
        assertThat(tireController).isNotNull();
    }
}
