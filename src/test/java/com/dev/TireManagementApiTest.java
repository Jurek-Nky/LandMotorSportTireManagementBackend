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
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
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
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
        race = new Race(LocalDate.of(2021, 02, 03), "eifel");
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
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()))).andReturn();
    }

    @Test
    public void TestGetTireById() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/id")
                        .param("id", String.valueOf(tire.getTireID()))
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void TestGetTiresByBez() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/bez")
                        .param("bez", tire.getBezeichnung())
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void TestGetTireBySerial() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/serial")
                        .param("serial", tire.getSerialNumber())
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void testGetAllTires() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/all")
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));

    }


    @Test
    public void TestGetTiresByRace() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(get("/api/v1/tire/race")
                        .param("raceid", String.valueOf(tire.getRace().getRaceID()))
                        .characterEncoding("UTF-8")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void TestDeleteTire() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());

        mockMvc.perform(delete("/api/v1/tire/delete/" + tire.getTireID())
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void TestUpdateTire() throws Exception {
        tire = tireRepository.save(new TestTire(race).getTire());
        Tire tire1 = new TestTire(race).getTire();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("spez", tire1.getSpez());
        map.add("serial", tire1.getSerialNumber());

        mockMvc.perform(put("/api/v1/tire/update/" + tire.getTireID())
                        .params(map)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

}