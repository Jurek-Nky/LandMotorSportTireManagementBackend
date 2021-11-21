package com.dev.reifen;

import com.dev.rennen.Rennen;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReifenController.class)
@AutoConfigureRestDocs
class ReifenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReifenService reifenService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getReifenById() {
    }

    @Test
    void getReifenByBez() {
    }

    @Test
    void getReifenBySerial() {
    }

    @Test
    void getAllReifens() throws Exception {
        Random rnd = new Random();
        String serialnumber = "abx" + rnd.nextInt(10000);
        String serialnumber2 = "abc" + rnd.nextInt(10000);
        Rennen rennen = new Rennen(new Date(2021, 6, 2), "eifel");
        List<Reifen> reifens = Arrays.asList(
                new Reifen(rennen, serialnumber, "cooler reifen", LocalDate.of(2021, 06, 03),
                        new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                        new Time(18, 30, 0), new Time(20, 0, 0)),
                new Reifen(rennen, serialnumber2, "cooler reifen", LocalDate.of(2021, 06, 03),
                        new Time(0), "med G/D", "Q2", 1.49, 1.52, 1.33, 1.37, 20, 90, 90,
                        new Time(18, 30, 0), new Time(20, 0, 0)));


        when(reifenService.getReifen()).thenReturn(reifens);

        mockMvc.perform(get("/api/v1/reifen/all"))
                .andExpect(status().isOk())
                .andDo(document("get-all-Reifens",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    void getReifensByRennen() {
    }

    @Test
    void registerNewReifen() {
    }

    @Test
    void deleteReifen() {
    }

    @Test
    void updateReifen() {
    }
}