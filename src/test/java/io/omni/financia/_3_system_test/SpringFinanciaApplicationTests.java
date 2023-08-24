package io.omni.financia._3_system_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.omni.financia.SpringFinanciaApplication;
import io.omni.financia.dto.AppUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
Integration testing is the phase in software testing in which
individual software modules are combined and tested as a group.

Integration testing is conducted to evaluate the compliance of a system or component
with specified functional requirements.

It occurs after unit testing and before system testing
*/


// https://www.baeldung.com/spring-boot-testing
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringFinanciaApplication.class)
@AutoConfigureMockMvc
// Probably this just loads additional configurations
// @TestPropertySource(locations = "classpath:application-integration-test.properties")
@ActiveProfiles("integration-test")
class SpringFinanciaApplicationTests {

    private @Autowired MockMvc mvc;
    private @Autowired ObjectMapper objectMapper;

    @Test
    void testCreateUser_succeeds() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(AppUserDto.builder()
                                .name("Abu Sufian")
                                .email("abu@mail.com")
                        .build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Abu Sufian")))
                .andExpect(jsonPath("$.email", is("abu@mail.com")));
    }

}
