package com.dev.reifen;

import com.dev.rennen.RennenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AllTireClassesInitTest {
    @Autowired
    private ReifenRepository reifenRepository;
    @Autowired
    private RennenRepository rennenRepository;
    @Autowired
    private ReifenService reifenService = new ReifenService(reifenRepository, rennenRepository);
    @Autowired
    private ReifenController reifenController = new ReifenController(reifenService);

    @Test
    void repositoryIsNotNull() {
        assertThat(reifenRepository).isNotNull();
    }

    @Test
    void serviceIsNotNull() {
        assertThat(reifenService).isNotNull();
    }

    @Test
    void controllerIsNotNull() {
        assertThat(reifenController).isNotNull();
    }
}
