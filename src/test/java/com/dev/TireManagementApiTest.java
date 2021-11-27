package com.dev;


import com.dev.race.Race;
import com.dev.race.RaceRepository;
import com.dev.tire.TestTire;
import com.dev.tire.Tire;
import com.dev.tire.TireDto;
import com.dev.tire.TireRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SpringDocsApplicationTests {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    RaceRepository raceRepository;
    @Autowired
    TireRepository tireRepository;

    private MockMvc mockMvc;
    private Race race;
    private TireDto tireDto;
    private TestTire testTire;
    private Tire tire;
    private String tireJson;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        race = new Race(LocalDate.of(2021, 2, 3), "eifel");
        race = raceRepository.save(race);
        tireRepository.deleteAll();
    }


    @Test
    public void testAddTire() throws Exception {
        testTire = new TestTire(race);
        tireDto = testTire.getTireDto();
        tire = testTire.getTire();

        tireJson = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(tireDto);

        mockMvc.perform(post("/api/v1/tire/new")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(tireJson))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void testGetTireById() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/id")
                        .param("id", String.valueOf(tire.getTireID()))
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTiresByBez() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/bez")
                        .param("bez", tire.getBezeichnung())
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTireBySerial() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/serial")
                        .param("serial", tire.getSerialNumber())
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllTires() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/all")
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    public void testGetTiresByRace() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/race")
                        .param("raceid", String.valueOf(tire.getRace().getRaceID()))
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTire() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(delete("/api/v1/tire/delete/" + tire.getTireID())
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .isOk());
    }

    @Test
    public void testUpdateTire() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());
        Tire tire1 = new TestTire(race).getTire();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("spez", tire1.getSpez());
        map.add("serial", tire1.getSerialNumber());

        mockMvc.perform(put("/api/v1/tire/update/" + tire.getTireID())
                        .params(map)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .isOk());
    }

}