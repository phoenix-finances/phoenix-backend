package io.omni.financia._2_integration_tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.omni.financia.config.AppConfig;
import io.omni.financia.config.WebSecurityConfig;
import io.omni.financia.controllers.UserController;
import io.omni.financia.domains.AppUser;
import io.omni.financia.repository.UserRepository;
import io.omni.financia.security.JwtAuthenticationEntryPoint;
import io.omni.financia.security.JwtAuthenticationFilter;
import io.omni.financia.security.JwtHelper;
import io.omni.financia.services.CustomUserDetailsService;
import io.omni.financia.services.impl.AppUserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(UserController.class)
// https://stackabuse.com/guide-to-unit-testing-spring-boot-rest-apis/
@WebMvcTest
// https://stackoverflow.com/questions/64540972/webmvctest-is-not-excluding-and-loading-beans-marked-as-repository
@ContextConfiguration(classes = {
        UserController.class,
        AppUserServiceImpl.class,
        CustomUserDetailsService.class,
        JwtAuthenticationEntryPoint.class,
        JwtAuthenticationFilter.class,
        JwtHelper.class,
        //SecurityTestConfig.class,
        WebSecurityConfig.class,
        AppConfig.class
})
@AutoConfigureMockMvc
public class UserControllerTest {

    // https://stackabuse.com/guide-to-unit-testing-spring-boot-rest-apis/

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @SuppressWarnings("unused")
	@Autowired
    private WebApplicationContext context;

    //@Autowired AppUserService appUserService;

    @MockBean UserRepository userRepository;
    //@MockBean PostRepository postRepository;

    AppUser USER_1 = AppUser.builder()
            .name("Abu Sufian")
            .email("sufian@mail.com")
            .password("123456")
            .build();
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

    protected MockHttpServletRequestBuilder jsonRequest(MockHttpServletRequestBuilder builder, Object content) throws JsonProcessingException {
        return builder.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(mapper.writeValueAsString(content));
    }

    @Test
    public void userRegister_success() throws Exception{
        Mockito.when(userRepository.save((AppUser) any(AppUser.class)))
                        .thenAnswer(invocation ->{
                            AppUser user = invocation.getArgument(0);
                            user.setId(1L);
                            return user;
                        });

        mockMvc.perform(jsonRequest(MockMvcRequestBuilders.post("/users"), USER_1))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", notNullValue()));
    }


    //@Test
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
