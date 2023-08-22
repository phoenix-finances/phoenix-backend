package io.omni.financia._2_integration_tests;

import io.omni.financia.config.WebSecurityConfig;
import io.omni.financia.controllers.UserController;
import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.repository.PostRepository;
import io.omni.financia.domains.repository.UserRepository;
import io.omni.financia.services.impl.AppUserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.omni.financia.services.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(UserController.class)
// https://stackabuse.com/guide-to-unit-testing-spring-boot-rest-apis/
@WebMvcTest
// https://stackoverflow.com/questions/64540972/webmvctest-is-not-excluding-and-loading-beans-marked-as-repository
@ContextConfiguration(classes = {
        UserController.class,
        PostServiceImpl.class,
        AppUserServiceImpl.class,
        //SecurityTestConfig.class,
        WebSecurityConfig.class
})
public class UserControllerTest {

    // https://stackabuse.com/guide-to-unit-testing-spring-boot-rest-apis/

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @SuppressWarnings("unused")
	@Autowired
    private WebApplicationContext context;

    //@Autowired AppUserService appUserService;

    @MockBean UserRepository userRepository;
    @MockBean PostRepository postRepository;

    AppUser USER_1 = AppUser.builder()
            //.name("Abu Sufian")
            .email("sufian@mail.com").build();
    AppUser USER_2 = AppUser.builder()
            //.name("Abu Sufian Milon")
            .email("sufian@mail.com").build();
    AppUser USER_3 = AppUser.builder()
            //.name("Esme Azom")
            .email("sufian@mail.com").build();

    /*@Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }*/


    @Test
    //@WithUserDetails("user_1")
    public void getAllUsers_success() throws Exception{
        List<AppUser> users = new ArrayList<>(List.of(USER_1, USER_2, USER_3));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].name", is("Abu Sufian Milon")));
    }

}
