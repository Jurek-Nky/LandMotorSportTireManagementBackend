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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        raceRepository.save(race);
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
    public void testGetTire() throws Exception {
        testTire = new TestTire(race);
        tire = testTire.getTire();
        tireRepository.save(tire);
        mockMvc.perform(get("/api/v1/tire/all")
                        .characterEncoding("UTF-8")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));

    }

}